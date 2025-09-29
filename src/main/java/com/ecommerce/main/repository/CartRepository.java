package com.ecommerce.main.repository;
import com.ecommerce.main.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart,Long> {
}
