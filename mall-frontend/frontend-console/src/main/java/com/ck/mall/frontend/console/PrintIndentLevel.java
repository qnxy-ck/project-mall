package com.ck.mall.frontend.console;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 打印缩进层级
 *
 * @author Qnxy
 */
@RequiredArgsConstructor
@Getter
public enum PrintIndentLevel {

    ZERO(""),
    ONE(getSpaces(4)),
    TWO(getSpaces(8)),
    ;

    private final String spaces;

    /**
     * 构建缩进数据
     *
     * @param spacesCount 空格数量
     * @return .
     */
    private static String getSpaces(int spacesCount) {
        return Stream.generate(() -> " ")
                .limit(spacesCount)
                .collect(Collectors.joining());
    }
}
