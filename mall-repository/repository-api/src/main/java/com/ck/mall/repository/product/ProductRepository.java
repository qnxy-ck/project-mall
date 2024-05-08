package com.ck.mall.repository.product;

import com.ck.mall.repository.ServiceLoaderUtil;
import com.ck.mall.repository.data.product.Product;

import java.util.Optional;

/**
 * 商品存储相关操作
 * 
 * @author Qnxy
 */
public interface ProductRepository {

    ProductRepository INSTANCE = ServiceLoaderUtil.find(ProductRepository.class);


    Optional<Product> findById(Long id);

}
