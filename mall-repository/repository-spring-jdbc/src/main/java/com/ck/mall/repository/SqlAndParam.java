package com.ck.mall.repository;

/**
 * sql和对应参数
 *
 * @author Qnxy
 */
public record SqlAndParam(String sql, Object param) {

    public SqlAndParam(Object param) {
        this("", param);
    }

}

