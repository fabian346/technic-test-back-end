package com.technicTestBackEnd.api.controller.product;

import com.technicTestBackEnd.api.model.request.product.ProductRequest;
import com.technicTestBackEnd.api.model.responses.product.ProductResponse;
import com.technicTestBackEnd.api.services.product.IProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api")
@CrossOrigin(origins = "*")
public class ProductController {

    private final IProductService iProductService;

    public ProductController(IProductService iProductService) {
        this.iProductService = iProductService;
    }

    @PostMapping("/product")
    public ResponseEntity addProduct(@RequestBody ProductRequest productRequest) {
        ProductResponse product = iProductService.createProduct(productRequest);
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    @GetMapping("/product/{id}")
    public ResponseEntity getProduct(@PathVariable Long id) {
        ProductResponse product = iProductService.getProductById(id);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @GetMapping("/product")
    public ResponseEntity<Page<ProductResponse>> getAllProducts(Pageable pageable) {
        Page<ProductResponse> products = iProductService.getAllProducts(pageable);
        return ResponseEntity.ok(products);
    }

}
