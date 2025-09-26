package com.ecommerce.main.repository;

import com.ecommerce.main.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product,Long> {

    List<Product> findByCategoryId(Long id);

    Optional<Product> findByName(String name);
}
