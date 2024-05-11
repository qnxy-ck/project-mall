package com.ck.mall.frontend.console;

import java.util.Optional;

/**
 * 枚举类型转换接口, 如果需要枚举类型可以快速转换实现该接口即可
 *
 * @author Qnxy
 */
@FunctionalInterface
public interface ConsoleEnumConverter<T extends Enum<T>> {

    /**
     * 根据 value 获取一个枚举类型
     *
     * @param value 输入的数据
     * @return 枚举
     */
    Optional<T> convert(int value);

}