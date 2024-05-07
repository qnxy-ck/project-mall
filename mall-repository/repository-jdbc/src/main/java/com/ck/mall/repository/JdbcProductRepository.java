package com.ck.mall.repository;

import com.ck.mall.repository.data.product.Product;
import com.ck.mall.repository.product.ProductRepository;

import java.util.Optional;

/**
 * @author Qnxy
 */
public class JdbcProductRepository implements ProductRepository {

    @Override
    public Optional<Product> findById(Long id) {
        System.out.println("jdbc 的查询实现");
        return Optional.empty();
    }
}
