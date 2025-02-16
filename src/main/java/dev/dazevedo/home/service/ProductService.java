package dev.dazevedo.home.service;

import dev.dazevedo.home.entity.Product;
import dev.dazevedo.home.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductService {
    private ProductRepository productReposirory;

    public List<Product> getAllProducts() {
        return productReposirory.findAll();
    }

    public Product createProduct(Product product) {
        return productReposirory.save(product);
    }

}
