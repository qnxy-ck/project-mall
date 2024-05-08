package com.ck.mall.repository.product;

import com.ck.mall.repository.data.product.Product;

import java.util.Optional;

/**
 * @author Qnxy
 */
public class SpringJdbcProductRepository implements ProductRepository {
    @Override
    public Optional<Product> findById(Long id) {
        System.out.println("spring de shixian");
        return Optional.empty();
    }
}
