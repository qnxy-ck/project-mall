package com.ck.mall.repository;

import java.security.SecureRandom;

/**
 * 随机获取手机号
 *
 * @author Qnxy
 */
public final class RandomPhoneNumber {
    private static final SecureRandom RANDOM = new SecureRandom();

    /**
     * 获取一个手机号
     */
    public static String nextPhoneNumber() {
        final StringBuilder sb = new StringBuilder();
        sb.append("155");

        for (int i = 0; i < 8; i++) {
            sb.append(RANDOM.nextInt(10));
        }

        return sb.toString();
    }
}
