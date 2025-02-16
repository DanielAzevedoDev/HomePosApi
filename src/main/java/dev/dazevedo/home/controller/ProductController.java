package dev.dazevedo.home.controller;

import dev.dazevedo.home.entity.Product;
import dev.dazevedo.home.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/product")
@AllArgsConstructor
public class ProductController {

    private ProductService productService;

    @GetMapping("/")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @PostMapping("/")
    public ResponseEntity<Product> createProduct(Product product) {
        return new ResponseEntity<>(productService.createProduct(product),HttpStatus.CREATED);
    }

}
