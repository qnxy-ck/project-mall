package com.ck.mall.repository;

import com.ck.mall.repository.data.user.UserInfo;
import com.ck.mall.repository.user.UserInfoRepository;
import org.junit.jupiter.api.Test;

import static com.ck.mall.repository.RandomPhoneNumber.nextPhoneNumber;

/**
 * 用户信息存储测试
 *
 * @author Qnxy
 */
public class UserInfoRepositoryTests {

    private final UserInfoRepository userInfoRepository = UserInfoRepository.INSTANCE;

    private static UserInfo getUserInfo() {
        UserInfo userInfo = new UserInfo();

        userInfo.setPhoneNumber(nextPhoneNumber());

        userInfo.setUsername("ck11");
        userInfo.setPassword("123");
        userInfo.setUserProfilePicture("/xxx/abc.jpg");
        userInfo.setUserType(UserInfo.UserType.ADMIN_USER);
        return userInfo;
    }


    @Test
    public void insertTest() {
        UserInfo userInfo = getUserInfo();
        assert userInfoRepository.insert(userInfo);

        System.out.println(userInfo.getId());
    }


    @Test
    public void findByPhoneNumberAndPasswordTest() {
        UserInfo userInfo = userInfoRepository.findByPhoneNumberAndPassword("15571912269", "1232")
                .orElse(null);

        System.out.println("userInfo = " + userInfo);

    }

    @Test
    public void findByIdTest() {
        assert userInfoRepository.findById(19L).isPresent();
    }

    @Test
    public void updateUserInfoByIdTest() {
        assert userInfoRepository.updateUserInfoById(55L, "ccck", "adasda", "aaa.jpg");

    }
}
