package com.technicTestBackEnd.api.services.product;

import com.technicTestBackEnd.api.model.request.product.ProductRequest;
import com.technicTestBackEnd.api.model.responses.product.ProductResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IProductService {

    ProductResponse createProduct(ProductRequest productRequest);

    ProductResponse getProductById(Long id);

    Page<ProductResponse> getAllProducts(Pageable pageable);
}
