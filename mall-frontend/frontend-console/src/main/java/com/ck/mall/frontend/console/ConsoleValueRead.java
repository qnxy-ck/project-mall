package com.ck.mall.frontend.console;

import java.util.Scanner;

/**
 * 控制台数据读取
 *
 * @author Qnxy
 */
public final class ConsoleValueRead {

    private static final Scanner SCANNER = new Scanner(System.in);


    private ConsoleValueRead() {
    }

    /**
     * 读取一个数据
     *
     * @param tips         读取数据时打印的输入提示
     * @param valueMapping 输入数据转换为期待的数据
     * @param <T>          .
     * @return 转换后的数据
     */
    public static <T> T nextValue(ConsoleTips tips, ConsoleValueMapping<T> valueMapping) {
        tips.printTextSuffix(": ");

        final String rawValue = SCANNER.nextLine();

        try {
            // 数据转换
            // 如果转换出错则递归到正确
            return valueMapping.mapping(rawValue);
        } catch (ConsoleValueMappingException e) {
            tips.printText(" - 输入错误 -> " + e.getMessage() + "\n");
            return nextValue(tips, valueMapping);
        } catch (Exception e) {

            // 转换函数没有处理的异常, 则直接报错
            throw new ConsoleValueMappingException(" - 转换出现未处理异常, 无法处理", e);
        }
    }

    /**
     * 读取一行数据, 直接返回
     *
     * @param tips 读取数据时打印的输入提示
     * @return 读取到的数据
     */
    public static String nextValue(ConsoleTips tips) {
        return nextValue(tips, it -> it);
    }

    /**
     * 读取数据并且转换为枚举类型
     * 该枚举类型必须实现 {@link ConsoleEnumConverter} 接口
     *
     * @param tips      读取数据时打印的输入提示
     * @param enumClass 需要转换的枚举 Class
     * @param <T>       .
     * @return 数据的枚举类型
     */
    public static <T extends Enum<T> & ConsoleEnumConverter<T>> T nextValue(ConsoleTips tips, Class<T> enumClass) {
        return nextValue(
                tips,
                it -> {

                    // 首先将输入的数据转换为 int 类型
                    // 如果失败则需要重新输入
                    final int enumVal;
                    try {
                        enumVal = Integer.parseInt(it);
                    } catch (NumberFormatException e) {
                        throw new ConsoleValueMappingException("不存在的类型");
                    }

                    // 在 class 中获取所有枚举类型
                    // 如果为空则直接抛出参数非法异常, 不可抛出转换异常(会导致用户重新输入, 然后仍然无法转换, 今日死循环)
                    final T[] enumConstants = enumClass.getEnumConstants();
                    if (enumConstants.length == 0) {
                        throw new IllegalArgumentException("此枚举类不存在任何枚举值: " + enumClass.getName());
                    }

                    // 使用枚举类中的第一个元素调用转换方法进行转换
                    return enumConstants[0].convert(enumVal)
                            .orElseThrow(() -> new ConsoleValueMappingException("类型不存在"));
                }
        );
    }


}
