package com.ck.mall.frontend.console;

import com.ck.mall.frontend.command.CommandData;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

/**
 * 控制台提示信息打印
 *
 * @author Qnxy
 */
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public final class ConsoleTips {

    /**
     * 打印的数据
     */
    private final String text;
    /**
     * 缩进层级
     */
    private final PrintIndentLevel indentLevel;


    /**
     * 打印命令类型信息
     * 限定类型必须为枚举并且该枚举实现了 {@link CommandData} 接口
     *
     * @param enumClass   需要打印的命令类
     * @param indentLevel 打印缩进
     */
    public static <E extends Enum<E> & CommandData> void printCommandInfo(Class<E> enumClass, PrintIndentLevel indentLevel) {
        final ConsoleTips tips = tips("-------------------------------\n", indentLevel);

        // 打印 '---' 
        tips.printText();

        // 反射获取枚举所有类型
        for (E e : enumClass.getEnumConstants()) {
            final String str = String.format("| %s. %s\n", e.getCmdIndex(), e.getCmdDesc());
            tips.printText(str);
        }

        tips.printText();
    }

    /**
     * 构建 ConsoleTips
     *
     * @param text        需要打印的数据
     * @param indentLevel 缩进层级
     * @return .
     */
    public static ConsoleTips tips(String text, PrintIndentLevel indentLevel) {
        return new ConsoleTips(text, indentLevel);
    }

    /**
     * 构建 ConsoleTips, 默认 {@link  PrintIndentLevel#ZERO}
     *
     * @param text 需要打印的数据
     * @return .
     */
    public static ConsoleTips tips(String text) {
        return new ConsoleTips(text, PrintIndentLevel.ZERO);
    }

    /**
     * 构建 ConsoleTips, 默认 {@link  PrintIndentLevel#ONE}
     *
     * @param text 需要打印的数据
     * @return .
     */
    public static ConsoleTips tipsOneLevel(String text) {
        return new ConsoleTips(text, PrintIndentLevel.ONE);
    }

    /**
     * 构建 ConsoleTips, 默认 {@link  PrintIndentLevel#TWO}
     *
     * @param text 需要打印的数据
     * @return .
     */
    public static ConsoleTips tipsTwoLevel(String text) {
        return new ConsoleTips(text, PrintIndentLevel.TWO);
    }

    // -----------------------------------------以下为类成员方法--------------------------------------

    /**
     * 使用当前类缩进打印数据, 和构建类时传人的打印数据无关
     *
     * @param text 需要打印的数据
     */

    public void printText(String text) {
        System.out.print(this.indentLevel.getSpaces() + text);
    }

    /**
     * 打印构建该类时的数据
     */
    public void printText() {
        System.out.print(getPrintText(null));
    }

    /**
     * 打印构建该类时的数据, 在打印时加上指定后缀
     *
     * @param suffix 后缀信息
     */
    public void printTextSuffix(String suffix) {
        System.out.print(getPrintText(suffix));
    }

    /**
     * 构建打印数据
     */
    private String getPrintText(String suffix) {
        final String r = this.indentLevel.getSpaces() + this.text;
        return suffix != null && !suffix.isBlank() ? r + suffix : r;
    }

}


