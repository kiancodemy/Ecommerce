package com.ecommerce.main.repository;

import com.ecommerce.main.dto.CartItemDto;
import com.ecommerce.main.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItem,Long> {

    Optional<CartItem> findByCartIdAndProductId(Long cartId, Long productId);

    CartItem findByCartId(Long cartId);

}
