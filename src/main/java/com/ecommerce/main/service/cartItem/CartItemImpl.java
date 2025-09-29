package com.ecommerce.main.service.cartItem;
import com.ecommerce.main.repository.CartItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartItemImpl {
    private final CartItemRepository cartItemRepository;

}
