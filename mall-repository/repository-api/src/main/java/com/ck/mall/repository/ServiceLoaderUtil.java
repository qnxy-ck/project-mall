package com.ck.mall.repository;

import java.util.ServiceLoader;

/**
 * @author Qnxy
 */
public class ServiceLoaderUtil {

    /**
     * 查找一个实现
     *
     * @param targetClass 需要查找的类型
     * @param <T>         .
     * @return 找到的实例
     */
    public static <T> T find(Class<T> targetClass) {

        return ServiceLoader.load(targetClass)
                .findFirst()
                .orElseThrow(() -> new ImplNotFoundException(targetClass));
    }
}
