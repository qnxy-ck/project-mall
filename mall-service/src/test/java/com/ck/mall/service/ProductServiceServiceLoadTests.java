package com.ck.mall.service;

import com.ck.mall.repository.data.product.Product;
import com.ck.mall.repository.product.ProductRepository;
import org.junit.jupiter.api.Test;

import java.util.Optional;

/**
 * 商品查询Spi机制实现测试
 *
 * @author Qnxy
 */
public class ProductServiceServiceLoadTests {


    @Test
    public void test() {
        ProductRepository productRepository = ProductRepository.INSTANCE;

        Optional<Product> byId = productRepository.findById(0L);


    }
}
