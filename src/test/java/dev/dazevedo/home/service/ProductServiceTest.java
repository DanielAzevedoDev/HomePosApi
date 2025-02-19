package dev.dazevedo.home.service;

import dev.dazevedo.home.entity.Product;
import dev.dazevedo.home.repository.ProductRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
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
    void testGetProductById_ProductExists() {
        // Arrange
        Product product = new Product(1L, "Product A", 10.0);
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        // Act
        Product result = productService.getProductById(1L);

        // Assert
        assertNotNull(result);
        assertEquals(product, result);
        verify(productRepository, times(1)).findById(1L);
    }

    @Test
    void testGetProductById_ProductDoesNotExist() {
        // Arrange
        when(productRepository.findById(1L)).thenReturn(Optional.empty());

        // Act
        Product result = productService.getProductById(1L);

        // Assert
        assertNull(result);
        verify(productRepository, times(1)).findById(1L);
    }

    @Test
    void testGetAllProducts() {
        // Arrange
        List<Product> products = List.of(
            new Product(1L, "Product A", 10.0),
            new Product(2L, "Product B", 20.0)
        );
        when(productRepository.findAll()).thenReturn(products);

        // Act
        List<Product> result = productService.getAllProducts();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Product A", result.get(0).getName());
        assertEquals("Product B", result.get(1).getName());
        verify(productRepository, times(1)).findAll();
    }

    @Test
    void testCreateProduct() {
        // Arrange
        Product product = new Product(null, "Product A", 10.0);
        Product savedProduct = new Product(1L, "Product A", 10.0);
        when(productRepository.save(product)).thenReturn(savedProduct);

        // Act
        Product result = productService.createProduct(product);

        // Assert
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Product A", result.getName());
        verify(productRepository, times(1)).save(product);
    }

    @Test
    void testUpdateProduct_ProductExists() {
        // Arrange
        Product existingProduct = new Product(1L, "Product A", 10.0);
        Product updatedProduct = new Product(1L, "Updated Product", 15.0);
        when(productRepository.findById(1L)).thenReturn(Optional.of(existingProduct));
        when(productRepository.save(existingProduct)).thenReturn(updatedProduct);

        // Act
        Product result = productService.updateProduct(1L, updatedProduct);

        // Assert
        assertNotNull(result);
        assertEquals("Updated Product", result.getName());
        assertEquals(15.0, result.getPrice());
        verify(productRepository, times(1)).findById(1L);
        verify(productRepository, times(1)).save(existingProduct);
    }

    @Test
    void testUpdateProduct_ProductDoesNotExist() {
        // Arrange
        Product updatedProduct = new Product(1L, "Updated Product", 15.0);
        when(productRepository.findById(1L)).thenReturn(Optional.empty());

        // Act
        Product result = productService.updateProduct(1L, updatedProduct);

        // Assert
        assertNull(result);
        verify(productRepository, times(1)).findById(1L);
        verify(productRepository, never()).save(any(Product.class));
    }
}