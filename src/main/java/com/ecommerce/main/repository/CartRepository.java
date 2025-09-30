package com.ecommerce.main.repository;
import com.ecommerce.main.model.Cart;
import com.ecommerce.main.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart,Long> {


}
