package dev.dazevedo.home.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

    @Test
    void testEquals_SameObject() {
        Product expectedProduct = new Product(1L, "Product A", 10.0);
        Product actualProduct = new Product(1L, "Product A", 10.0);
        assertEquals(expectedProduct, actualProduct, "Equals should return true for the same object");
    }

    @Test
    void testEquals_DifferentClasses() {
        Product product = new Product(1L, "Product A", 10.0);
        Object nonProduct = new Object();
        assertNotEquals(product, nonProduct, "Equals should return false for objects of different classes");
    }

    @Test
    void testEquals_NullObject() {
        Product product = new Product(1L, "Product A", 10.0);
        assertNotEquals(null,product, "Equals should return false when comparing to null");
    }

    @Test
    void testEquals_DifferentId() {
        Product product1 = new Product(1L, "Product A", 10.0);
        Product product2 = new Product(2L, "Product A", 10.0);
        assertNotEquals(product1, product2, "Equals should return false for objects with different ids");
    }

    @Test
    void testGettersAndSetters() {
        Product product = new Product();
        product.setId(1L);
        product.setName("Product A");
        product.setPrice(10.0);

        assertEquals(1L, product.getId(), "Getter for 'id' not working properly");
        assertEquals("Product A", product.getName(), "Getter for 'name' not working properly");
        assertEquals(10.0, product.getPrice(), "Getter for 'price' not working properly");
    }

    @Test
    void testToString() {
        Product product = new Product(1L, "Product A", 10.0);
        String expectedString = "Product(id=1, name=Product A, price=10.0)";
        assertEquals(expectedString, product.toString(), "toString method does not match expected format");
    }
}
