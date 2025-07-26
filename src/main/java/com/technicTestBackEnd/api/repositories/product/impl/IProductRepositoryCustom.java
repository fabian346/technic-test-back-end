package com.technicTestBackEnd.api.repositories.product.impl;

import com.technicTestBackEnd.api.model.entities.product.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IProductRepositoryCustom {

    Product saveProduct(Product product);

    Product findProductById(long id);

    Page<Product> findAllProducts(Pageable pageable);
}
