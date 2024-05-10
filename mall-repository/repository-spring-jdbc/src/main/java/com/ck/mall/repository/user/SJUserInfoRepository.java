package com.ck.mall.repository.user;

import com.ck.mall.repository.SetInsertId;
import com.ck.mall.repository.SpringJdbcSupport;
import com.ck.mall.repository.SqlAndParam;
import com.ck.mall.repository.data.user.UserInfo;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.ck.mall.repository.EnumBeanPropertyParameterSource.enumValueSource;

/**
 * 用户信息存储操作
 *
 * @author Qnxy
 */
public class SJUserInfoRepository implements UserInfoRepository {

    @Override
    public boolean insert(UserInfo userInfo) {
        return SetInsertId.longIdBool(
                userInfo::setId,
                () -> SpringJdbcSupport.jdbcClient()
                        .sql("insert into user_info ( phone_number, username, password, user_profile_picture, user_type) " +
                                "value (:phoneNumber, :username, :password, :userProfilePicture, :userType)")
                        .paramSource(enumValueSource(userInfo))
        );
    }

    @Override
    public Optional<UserInfo> findByPhoneNumberAndPassword(String phoneNumber, String password) {
        return SpringJdbcSupport.jdbcClient()
                .sql("select * from user_info where phone_number = :phoneNumber and password = :password")
                .param("phoneNumber", phoneNumber)
                .param("password", password)
                .query(UserInfo.class)
                .optional();
    }

    @Override
    public Optional<UserInfo> findById(Long id) {
        return SpringJdbcSupport.jdbcClient()
                .sql("select * from user_info where id = ?")
                .param(id)
                .query(UserInfo.class)
                .optional();
    }

    @Override
    public boolean updateUserInfoById(Long id, String username, String password, String userProfilePicture) {
        final List<SqlAndParam> sqlAndParams = new ArrayList<>();

        if (StringUtils.hasText(username)) {
            sqlAndParams.add(new SqlAndParam(" username = ?", username));
        }

        if (StringUtils.hasText(password)) {
            sqlAndParams.add(new SqlAndParam(" password = ?", password));
        }

        if (StringUtils.hasText(userProfilePicture)) {
            sqlAndParams.add(new SqlAndParam(" user_profile_picture = ?", userProfilePicture));
        }

        if (sqlAndParams.isEmpty()) {
            return true;
        }

        final String sql = sqlAndParams.stream()
                .map(SqlAndParam::sql)
                .collect(Collectors.joining(",", "update user_info set", " where id = ?"));


        sqlAndParams.add(new SqlAndParam(id));

        List<Object> params = sqlAndParams.stream()
                .map(SqlAndParam::param)
                .collect(Collectors.toList());


        int update = SpringJdbcSupport.jdbcClient()
                .sql(sql)
                .params(params)
                .update();

        return update > 0;
    }


}
