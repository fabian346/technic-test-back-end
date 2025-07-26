package com.technicTestBackEnd.api.model.mapper.product;

import com.technicTestBackEnd.api.model.entities.product.Product;
import com.technicTestBackEnd.api.model.request.product.ProductRequest;
import com.technicTestBackEnd.api.model.responses.product.ProductResponse;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public static Product toEntity(ProductRequest request) {
        Product product = new Product();
        product.setName(request.getName());
        product.setPrice(request.getPrice());
        product.setDescription(request.getDescription());
        return product;
    }

    public static @NotNull ProductResponse toResponse(Product entity) {
        ProductResponse response = new ProductResponse();
        response.setId(entity.getId());
        response.setName(entity.getName());
        response.setPrice(entity.getPrice());
        response.setDescription(entity.getDescription());
        return response;
    }
}
