package com.ck.mall.repository;

import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * 在插入数据时填充id
 *
 * @author Qnxy
 */
public class SetInsertId {

    /**
     * 填充id并返回影响行数
     *
     * @param f             获取Id的方式
     * @param setIdConsumer 设置id
     * @param supplier      插入语句
     * @param <T>           .
     * @return 影响行数
     */
    private static <T> int execute(Function<Number, T> f, Consumer<T> setIdConsumer, Supplier<JdbcClient.StatementSpec> supplier) {
        final KeyHolder keyHolder = new GeneratedKeyHolder();
        int i = supplier.get().update(keyHolder);
        if (i > 0) {
            T n = f.apply(keyHolder.getKey());
            setIdConsumer.accept(n);
        }

        return i;
    }

    /**
     * 填充id并返回影响行数
     *
     * @param setIdConsumer 设置id
     * @param supplier      插入语句
     * @return 影响行数
     */
    public static int longId(Consumer<Long> setIdConsumer, Supplier<JdbcClient.StatementSpec> supplier) {
        return execute(Number::longValue, setIdConsumer, supplier);
    }

    /**
     * 填充id并返回影响行数
     *
     * @param setIdConsumer 设置id
     * @param supplier      插入语句
     * @return 是否插入成功
     */
    public static boolean longIdBool(Consumer<Long> setIdConsumer, Supplier<JdbcClient.StatementSpec> supplier) {
        return longId(setIdConsumer, supplier) > 0;
    }


}
