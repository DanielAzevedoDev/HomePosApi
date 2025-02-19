package dev.dazevedo.home.service;

import dev.dazevedo.home.entity.Product;
import dev.dazevedo.home.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductService {
    private ProductRepository productRepository;

    public Product getProductById(Long id) { return productRepository.findById(id).orElse(null);}

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public Product updateProduct(Long id, Product product) {

        Product existingProduct = productRepository.findById(id).orElse(null);

        if (existingProduct != null) {
            // Atualiza os campos necessários
            existingProduct.setName(product.getName());
            existingProduct.setPrice(product.getPrice());
            // Salva as alterações
            return productRepository.save(existingProduct);
        }

        return null;
    }
}
