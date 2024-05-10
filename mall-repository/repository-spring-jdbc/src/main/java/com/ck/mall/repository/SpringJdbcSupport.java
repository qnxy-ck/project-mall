package com.ck.mall.repository;

import org.springframework.jdbc.core.simple.JdbcClient;

/**
 * jdbc工具
 *
 * @author Qnxy
 */
public class SpringJdbcSupport {

    /**
     * 获取jdbc执行器客户端封装
     */
    private static final JdbcClient JDBC_CLIENT = JdbcClient.create(MallProjectDataSource.getDataSource());

    /**
     * 获取jdbc执行器
     */
    public static JdbcClient jdbcClient() {
        return JDBC_CLIENT;
    }

}
