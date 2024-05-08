package com.ck.mall.repository.user;

import com.ck.mall.repository.ServiceLoaderUtil;
import com.ck.mall.repository.data.user.UserInfo;

import java.util.Optional;

/**
 * 用户信息相关存储操作
 *
 * @author Qnxy
 */
public interface UserInfoRepository {

    UserInfoRepository INSTANCE = ServiceLoaderUtil.find(UserInfoRepository.class);


    /**
     * 增加一条新纪录
     *
     * @param userInfo 待添加的数据
     * @return 数据影响条数
     */
    int insert(UserInfo userInfo);

    /**
     * 根据用户名和密码查询一条数据
     *
     * @param username 用户名
     * @param password 用户密码
     * @return .
     */
    Optional<UserInfo> findByUsernameAndPassword(String username, String password);


}
