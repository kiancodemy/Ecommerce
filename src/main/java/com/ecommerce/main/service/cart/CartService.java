package com.ecommerce.main.service.cart;

import com.ecommerce.main.dto.CartItemDto;
import com.ecommerce.main.model.Cart;
import com.ecommerce.main.model.CartItem;

import java.math.BigDecimal;
import java.util.List;

public interface CartService {
    Cart getCart(Long cartId);
    void deleteCart(Long cartId);
    void clearCart(Long cartId);
    BigDecimal getTotalPrice(Long cartId);


    Cart addItemToCart(Long cartId, Long productId, int quantity);

    void removeItemFromCart(Long cartId, Long productId);
}
