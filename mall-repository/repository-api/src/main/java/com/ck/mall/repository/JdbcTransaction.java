package com.ck.mall.repository;

import java.util.function.Supplier;

/**
 * jdbc 事务管理
 *
 * @author Qnxy
 */
public interface JdbcTransaction {

    JdbcTransaction INSTANCE = ServiceLoaderUtil.find(JdbcTransaction.class);

    /**
     * 开启并执行事务
     *
     * @param executor 执行逻辑
     * @param <T>      .
     * @return 执行逻辑返回的结果
     */
    <T> T executeTransaction(Supplier<T> executor);

    /**
     * 开启并执行事务, 没有返回值
     *
     * @param executor 执行逻辑
     */
    void executeTransaction(Runnable executor);

}
