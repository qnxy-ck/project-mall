package com.ck.mall.frontend;

import com.ck.mall.frontend.command.CommandData;
import com.ck.mall.frontend.console.ConsoleEnumConverter;

import java.util.Arrays;
import java.util.Optional;

/**
 * @author Qnxy
 */
@Deprecated(since = "测试使用, 后续删除")
public enum DemoUserGenderE implements ConsoleEnumConverter<DemoUserGenderE>, CommandData {
    MALE,
    FEMALE,
    ;


    @Override
    public Optional<DemoUserGenderE> convert(int value) {
        return Arrays.stream(values())
                .filter(it -> it.ordinal() == value)
                .findFirst();
    }

    @Override
    public int getCmdIndex() {
        return ordinal();
    }

    @Override
    public String getCmdDesc() {
        return name();
    }


}
