package com.ck.mall.repository;

/**
 * 实现类未找到异常
 *
 * @author Qnxy
 */
public class ImplNotFoundException extends RuntimeException {
    public ImplNotFoundException(Class<?> clazz) {
        super("实现类: " + clazz + " 未找到.");
    }
}
