package com.technicTestBackEnd.api.repositories.product;

import com.technicTestBackEnd.api.model.entities.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductRepository extends JpaRepository<Product, Long> {
}
