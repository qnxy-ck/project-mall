package com.ck.mall.repository;

import org.springframework.jdbc.support.JdbcTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.function.Supplier;

/**
 * Spring jdbc 实现的事务管理
 *
 * @author Qnxy
 */
public final class SpringJdbcTransaction implements JdbcTransaction {

    /**
     * jdbc事物处理器
     */
    private static final TransactionTemplate TRANSACTION_TEMPLATE;

    static {
        // 获取jdbc事物管理器
        PlatformTransactionManager transactionManager = new JdbcTransactionManager(MallProjectDataSource.getDataSource());
        TRANSACTION_TEMPLATE = new TransactionTemplate(transactionManager);
    }


    @Override
    public <T> T executeTransaction(Supplier<T> executor) {
        return TRANSACTION_TEMPLATE.execute(it -> executor.get());
    }

    @Override
    public void executeTransaction(Runnable executor) {
        TRANSACTION_TEMPLATE.executeWithoutResult(it -> executor.run());
    }
}
