package com.ck.mall.repository.data.user;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 用户信息
 *
 * @author Qnxy
 */
@Data
public class UserInfo {

    /**
     * 用户id
     */
    private Long id;

    /**
     * 用户手机号(用于登录), 必填
     */
    private String phoneNumber;

    /**
     * 用户名称 (必填)
     */
    private String username;

    /**
     * 登陆密码 (必填)
     */
    private String password;

    /**
     * 用户头像地址 (必填)
     */
    private String userProfilePicture;

    /**
     * 创建时间 (必填)
     */
    private LocalDateTime createdAt;

    /**
     * 更新时间 (必填)
     */
    private LocalDateTime updatedAt;

}
