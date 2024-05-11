package com.ck.mall.frontend.command;

import com.ck.mall.frontend.console.ConsoleEnumConverter;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.Optional;

/**
 * 演示使用, 后续删除
 *
 * @author Qnxy
 */
@RequiredArgsConstructor
@Getter
@Deprecated(since = "演示使用, 后续删除")
public enum DemoCommandE implements CommandData, ConsoleEnumConverter<DemoCommandE> {
    SELECT_ALL("查询所有"),
    SELECT_BY_ID("根据id查询"),
    SELECT_PAGE("分页查询"),


    ;

    private final String cmdDesc;


    @Override
    public int getCmdIndex() {
        return ordinal();
    }

    @Override
    public Optional<DemoCommandE> convert(int value) {
        return Arrays.stream(values())
                .filter(v -> v.getCmdIndex() == value)
                .findFirst();
    }

    @Override
    public String toString() {
        return "RootEnum{" +
                "cmdIndex='" + getCmdIndex() + '\'' +
                "cmdDesc='" + cmdDesc + '\'' +
                '}';
    }
}
