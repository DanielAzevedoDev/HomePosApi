package dev.dazevedo.home.controller;

import dev.dazevedo.home.entity.Product;
import dev.dazevedo.home.service.ProductService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductControllerTest {

    @InjectMocks
    private ProductController productController;

    @Mock
    private ProductService productService;

    private AutoCloseable mocks;

    @BeforeEach
    void setUp() {
        mocks = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        mocks.close();
    }


    @Test
    void testGetAllProducts() {
        // Arrange
        Product product1 = new Product(1L, "Product 1", 100.0);
        Product product2 = new Product(2L, "Product 2", 200.0);
        List<Product> productList = Arrays.asList(product1, product2);

        when(productService.getAllProducts()).thenReturn(productList);

        // Act
        List<Product> result = productController.getAllProducts();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(productService, times(1)).getAllProducts();
    }

    @Test
    void testCreateProduct() {
        // Arrange
        Product product = new Product(1L, "Product 1", 100.0);
        when(productService.createProduct(product)).thenReturn(product);

        // Act
        ResponseEntity<Product> response = productController.createProduct(product);

        // Assert
        assertNotNull(response);
        assertEquals(201, response.getStatusCode().value());
        assertEquals(product, response.getBody());
        verify(productService, times(1)).createProduct(product);
    }

    @Test
    void testUpdateProduct() {
        // Arrange
        Long productId = 1L;
        Product updatedProduct = new Product(productId, "Updated Product", 150.0);
        when(productService.updateProduct(eq(productId), any(Product.class))).thenReturn(updatedProduct);

        // Act
        ResponseEntity<Product> response = productController.updateProduct(productId, updatedProduct);

        // Assert
        assertNotNull(response);
        assertEquals(200, response.getStatusCode().value());
        assertEquals(updatedProduct, response.getBody());
        verify(productService, times(1)).updateProduct(productId, updatedProduct);
    }

    @Test
    void testUpdateProductNotFound() {
        // Arrange
        Long productId = 1L;
        when(productService.updateProduct(eq(productId), any(Product.class))).thenReturn(null);

        // Act
        ResponseEntity<Product> response = productController.updateProduct(productId, new Product());

        // Assert
        assertNotNull(response);
        assertEquals(404, response.getStatusCode().value());
        verify(productService, times(1)).updateProduct(eq(productId), any(Product.class));
    }

    @Test
    void testGetProductById() {
        // Arrange
        Long productId = 1L;
        Product product = new Product(productId, "Product 1", 100.0);
        when(productService.getProductById(productId)).thenReturn(product);

        // Act
        ResponseEntity<Product> response = productController.getProductById(productId);

        // Assert
        assertNotNull(response);
        assertEquals(200, response.getStatusCode().value());
        assertEquals(product, response.getBody());
        verify(productService, times(1)).getProductById(productId);
    }

    @Test
    void testGetProductByIdNotFound() {
        // Arrange
        Long productId = 1L;
        when(productService.getProductById(productId)).thenReturn(null);

        // Act
        ResponseEntity<Product> response = productController.getProductById(productId);

        // Assert
        assertNotNull(response);
        assertEquals(404, response.getStatusCode().value());
        verify(productService, times(1)).getProductById(productId);
    }
}