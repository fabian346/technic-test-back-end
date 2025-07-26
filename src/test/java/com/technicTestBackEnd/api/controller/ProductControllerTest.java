package com.technicTestBackEnd.api.controller;

import com.technicTestBackEnd.api.controller.product.ProductController;
import com.technicTestBackEnd.api.model.request.product.ProductRequest;
import com.technicTestBackEnd.api.model.responses.product.ProductResponse;
import com.technicTestBackEnd.api.services.product.IProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ProductControllerTest {

    @Mock
    private IProductService productService;

    @InjectMocks
    private ProductController productController;

    private ProductRequest request;
    private ProductResponse response;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        request = new ProductRequest();
        request.setName("Laptop");
        request.setPrice(new BigDecimal("1200.00"));
        request.setDescription("High-end gaming laptop");


    }

    @Test
    void testAddProduct() {

        response = new ProductResponse( "Laptop", new BigDecimal("1200.00"), "High-end gaming laptop");
        when(productService.createProduct(request)).thenReturn(response);

        ResponseEntity result = productController.addProduct(request);

        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertEquals(response, result.getBody());
        verify(productService, times(1)).createProduct(request);
    }

    @Test
    void testGetProductById() {

        when(productService.getProductById(1L)).thenReturn(response);

        ResponseEntity result = productController.getProduct(1L);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(response, result.getBody());
        verify(productService, times(1)).getProductById(1L);
    }

    @Test
    void testGetAllProducts() {

        response = new ProductResponse( "Laptop", new BigDecimal("1200.00"), "High-end gaming laptop");

        Pageable pageable = PageRequest.of(0, 10);
        Page<ProductResponse> page = new PageImpl<>(List.of(response));

        when(productService.getAllProducts(pageable)).thenReturn(page);

        ResponseEntity<Page<ProductResponse>> result = productController.getAllProducts(pageable);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(1, result.getBody().getTotalElements());
        //assertEquals("Laptop", result.getBody().getContent().get(0).getName());
        verify(productService, times(1)).getAllProducts(pageable);
    }
}