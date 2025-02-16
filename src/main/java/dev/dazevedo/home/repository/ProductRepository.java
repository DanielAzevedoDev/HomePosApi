package dev.dazevedo.home.repository;

import dev.dazevedo.home.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
