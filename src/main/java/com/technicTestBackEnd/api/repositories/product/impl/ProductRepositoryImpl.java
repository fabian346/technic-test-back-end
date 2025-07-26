package com.technicTestBackEnd.api.repositories.product.impl;

import com.technicTestBackEnd.api.exceptions.ProductNotFoundException;
import com.technicTestBackEnd.api.model.entities.product.Product;
import com.technicTestBackEnd.api.repositories.product.IProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ProductRepositoryImpl implements IProductRepositoryCustom {

    private final IProductRepository iProductRepository;

    public ProductRepositoryImpl(IProductRepository iProductRepository) {
        this.iProductRepository = iProductRepository;
    }

    @Override
    public Product saveProduct(Product product) {
        return iProductRepository.save(product);
    }

    @Override
    public Product findProductById(long id) {
        return iProductRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
    }

    @Override
    public Page<Product> findAllProducts(Pageable pageable) {
        return iProductRepository.findAll(pageable);
    }
}
