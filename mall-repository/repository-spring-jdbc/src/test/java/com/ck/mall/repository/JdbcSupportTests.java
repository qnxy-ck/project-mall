package com.ck.mall.repository;

import com.ck.mall.repository.data.user.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.security.SecureRandom;
import java.util.Objects;

/**
 * @author Qnxy
 */
@Slf4j
public class JdbcSupportTests {


//    private static final Logger log = LoggerFactory.getLogger(JdbcSupportTests.class);

    private static final SecureRandom RANDOM = new SecureRandom();

    /**
     * 随机获取一个手机号
     */
    private static String nextPhoneNumber() {
        return String.valueOf(RANDOM.nextInt(1, 99999));
    }

    /**
     * 构建用户信息
     */
    private static UserInfo getUserInfo() {
        UserInfo userInfo = new UserInfo();

        userInfo.setPhoneNumber(nextPhoneNumber());

        userInfo.setUsername("ck11");
        userInfo.setPassword("123");
        userInfo.setUserProfilePicture("/xxx/abc.jpg");
        return userInfo;
    }


    /**
     * 插入数据 方式1
     */
    @Test
    public void insertUserInfoTypeOne() {

        int update = JdbcSupport.jdbcClient()
                .sql("insert into user_info (phone_number, username, password, user_profile_picture) " +
                        "values (?, ?, ?, ?)")
                .param(1, nextPhoneNumber())
                .param(2, "username")
                .param(3, "password")
                .param(4, "user_profile_picture")
                .update();

        assert update > 0;
    }

    /**
     * 插入数据方式二, 实体类的方式
     */
    @Test
    public void insertUserInfoTypeTwo() {
        UserInfo userInfo = getUserInfo();

        int update = JdbcSupport.jdbcClient()
                .sql("insert into user_info (phone_number, username, password, user_profile_picture) " +
                        "values (:phoneNumber, :username, :password, :userProfilePicture)")
                .paramSource(userInfo)
                .update();

        assert update > 0;
    }

    /**
     * 插入数据方式三 获取自增ID
     */
    @Test
    public void insertAndReturnId() {
        UserInfo userInfo = getUserInfo();

        final KeyHolder keyHolder = new GeneratedKeyHolder();

        int update = JdbcSupport.jdbcClient()
                .sql("insert into user_info (phone_number, username, password, user_profile_picture) " +
                        "values (:phoneNumber, :username, :password, :userProfilePicture)")
                .paramSource(userInfo)
                .update(keyHolder);


        final Long id = Objects.requireNonNull(keyHolder.getKey()).longValue();
        userInfo.setId(id);

        assert update > 0;

        assert userInfo.getId() != null && userInfo.getId() > 0;
        log.info("insert id: {}", userInfo.getId());

    }

    /**
     * 查询所有信息
     */
    @Test
    public void selectAll() {
        JdbcClient jdbcClient = JdbcSupport.jdbcClient();

        jdbcClient.sql("select * from user_info")
                .query(UserInfo.class)
                .list()
                .forEach(it -> log.info(it.toString()));
    }

    /**
     * 根据id查询一条数据
     */
    @Test
    public void selectByIdOptional() {
        JdbcSupport.jdbcClient()
                .sql("select * from user_info where id = ?")
                .param(1, 3)
                .query(UserInfo.class)
                .optional()
                .ifPresent(it -> log.info(it.toString()));
    }

    /**
     * 根据用户名和密码查询一条数据
     */
    @Test
    public void selectByUsernameAndPasswordOptional() {
        JdbcSupport.jdbcClient()
                .sql("select * from user_info where username = ? and password = ? ")
                .param(1, "ck")
                .param(2, "admin")
                .query(UserInfo.class)
                .optional()
                .ifPresent(it -> log.info(it.toString()));
    }

    /**
     * a
     * 删除数据
     */
    @Test
    public void deleteById() {
        int update = JdbcSupport.jdbcClient()
                .sql("delete from user_info where id = ?")
                .param(1, 2)
                .update();

        assert update > 0;
    }

    /**
     * 事物
     */
    @Test
    public void transactionTest() {
        UserInfo userInfo = JdbcSupport.transactionTemplate().execute(it -> {
            JdbcSupport.jdbcClient()
                    .sql("update user_info set user_profile_picture = ? where id = ?")
                    .param(1, "ccc.jpg")
                    .param(2, 2)
                    .update();

            int i = 1 / 0;

            return JdbcSupport.jdbcClient()
                    .sql("select * from user_info where id = ?")
                    .param(1, 2)
                    .query(UserInfo.class).single();
        });

        log.info("userInfo: {}", userInfo);


    }
}
