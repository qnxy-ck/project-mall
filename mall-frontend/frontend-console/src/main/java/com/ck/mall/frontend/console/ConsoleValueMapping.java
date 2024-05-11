package com.ck.mall.frontend.console;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

/**
 * 控制台数据数据映射到一个具体的类型
 *
 * @author Qnxy
 */
@FunctionalInterface
public interface ConsoleValueMapping<T> {

    /**
     * 数据转换
     *
     * @param value 实际输入的数据
     * @return 转换后的数据
     * @throws ConsoleValueMappingException 实现类如果抛出该异常则表示转换出现问题, 需要用户重新输入并且转换
     *                                      直到不在出现该异常后输入结束.
     */
    T mapping(String value) throws ConsoleValueMappingException;


    /**
     * 提供一些常见的转换操作
     */
    final class ValueMappings {

        private static final Map<String, DateTimeFormatter> DATE_TIME_FORMATTER_MAP = new HashMap<>();

        private ValueMappings() {
        }

        /**
         * 转换成 int 类型
         *
         * @param value 实际输入的数据
         * @return int
         * @throws ConsoleValueMappingException .
         */
        public static Integer toInteger(String value) throws ConsoleValueMappingException {
            try {
                return Integer.parseInt(value);
            } catch (NumberFormatException e) {
                throw new ConsoleValueMappingException("输入字符不是一个数值");
            }
        }

        /**
         * 转换成 long 类型
         *
         * @param value 实际输入的数据
         * @return int
         * @throws ConsoleValueMappingException .
         */
        public static Long toLong(String value) throws ConsoleValueMappingException {
            try {
                return Long.parseLong(value);
            } catch (NumberFormatException e) {
                throw new ConsoleValueMappingException("输入字符不是一个数值");
            }
        }

        /**
         * 从缓存获取一个日期转换格式, 如果已存在的话
         * 不存在则会立即创建一个新的病返回
         *
         * @param format 转换的格式
         * @return 日期格式
         */
        private static DateTimeFormatter getDateTimeFormatter(String format) {
            return DATE_TIME_FORMATTER_MAP.computeIfAbsent(
                    format,
                    it -> {
                        try {
                            return DateTimeFormatter.ofPattern(format);
                        } catch (Exception e) {
                            throw new IllegalArgumentException("错误的日期格式: " + format);
                        }
                    }
            );
        }

        /**
         * 转换成 {@link LocalDate} 类型
         *
         * @param value      实际输入的数据
         * @param dateFormat 转换格式
         * @return .
         * @throws ConsoleValueMappingException .
         */
        public static LocalDate toLocalDate(String value, String dateFormat) throws ConsoleValueMappingException {
            final DateTimeFormatter timeFormatter = getDateTimeFormatter(dateFormat);

            try {
                return LocalDate.parse(value, timeFormatter);
            } catch (Exception e) {
                throw new ConsoleValueMappingException("期待的日期格式: " + dateFormat + " 实际输入为: " + value);
            }
        }

        /**
         * 转换成 {@link LocalDate} 类型, 转换格式默认为 "yyyy-MM-dd"
         *
         * @param value 实际输入的数据
         * @return .
         * @throws ConsoleValueMappingException .
         */
        public static LocalDate toLocalDate(String value) throws ConsoleValueMappingException {
            return toLocalDate(value, "yyyy-MM-dd");
        }

        /**
         * 转换成 {@link LocalDateTime} 类型
         *
         * @param value      实际输入的数据
         * @param dateFormat 转换格式
         * @return .
         * @throws ConsoleValueMappingException .
         */
        public static LocalDateTime toLocalDateTime(String value, String dateFormat) throws ConsoleValueMappingException {
            final DateTimeFormatter timeFormatter = getDateTimeFormatter(dateFormat);

            try {
                return LocalDateTime.parse(value, timeFormatter);
            } catch (Exception e) {
                throw new ConsoleValueMappingException("期待的日期格式: " + dateFormat + " 实际输入为: " + value);
            }
        }

        /**
         * 转换成 {@link LocalDateTime} 类型, 转换格式默认为 "yyyy-MM-dd HH:mm:ss"
         *
         * @param value 实际输入的数据
         * @return .
         * @throws ConsoleValueMappingException .
         */
        public static LocalDateTime toLocalDateTime(String value) throws ConsoleValueMappingException {
            return toLocalDateTime(value, "yyyy-MM-dd HH:mm:ss");
        }


        public static String toPhoneNumber(String value) throws ConsoleValueMappingException {
            // TODO 2024-05-11 使用正则实现手机号验证
            return null;
        }

    }


}