package com.ecommerce.main.service.cart;

import com.ecommerce.main.model.Cart;

import java.math.BigDecimal;

public interface CartService {
    Cart getCart(Long cartId);
    void deleteCart(Long cartId);
    void clearCart(Long cartId);
    BigDecimal getTotalPrice(Long cartId);


}
