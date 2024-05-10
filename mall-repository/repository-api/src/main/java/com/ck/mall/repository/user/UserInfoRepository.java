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
     * @return 是否添加成功
     */
    boolean insert(UserInfo userInfo);

    /**
     * 根据手机号和密码查询一条数据
     *
     * @param phoneNumber 用户手机号
     * @param password    用户密码
     * @return 用户信息
     */
    Optional<UserInfo> findByPhoneNumberAndPassword(String phoneNumber, String password);


    /**
     * 根据id查询一条用户信息
     *
     * @param id 用户id
     * @return 用户信息
     */
    Optional<UserInfo> findById(Long id);

    /**
     * 根据id更新用户信息
     *
     * @param id                 用户id
     * @param username           用户名称
     * @param password           用户密码
     * @param userProfilePicture 用户头像地址
     * @return 是否成功
     */
    boolean updateUserInfoById(Long id, String username, String password, String userProfilePicture);


}
