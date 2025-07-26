package com.technicTestBackEnd.api.services.product.impl;

import com.technicTestBackEnd.api.model.entities.product.Product;
import com.technicTestBackEnd.api.model.mapper.product.ProductMapper;
import com.technicTestBackEnd.api.model.request.product.ProductRequest;
import com.technicTestBackEnd.api.model.responses.product.ProductResponse;
import com.technicTestBackEnd.api.repositories.product.impl.IProductRepositoryCustom;
import com.technicTestBackEnd.api.services.product.IProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProductService implements IProductService {

    private final ProductMapper productMapper;
    private final IProductRepositoryCustom iProductRepo;

    public ProductService(ProductMapper productMapper, IProductRepositoryCustom iProductRepo) {
        this.productMapper = productMapper;
        this.iProductRepo = iProductRepo;
    }

    @Override
    public ProductResponse createProduct(ProductRequest productRequest) {

        Product productEntity = productMapper.toEntity(productRequest);
        Product product = iProductRepo.saveProduct(productEntity);
        ProductResponse productResponse = productMapper.toResponse(product);

        return productResponse;
    }

    @Override
    public ProductResponse getProductById(Long id) {
        Product product = iProductRepo.findProductById(id);
        return productMapper.toResponse(product);
    }

    @Override
    public Page<ProductResponse> getAllProducts(Pageable pageable) {

        Page<Product> entityPage = iProductRepo.findAllProducts(pageable);
        return entityPage.map(ProductMapper::toResponse);

    }
}
