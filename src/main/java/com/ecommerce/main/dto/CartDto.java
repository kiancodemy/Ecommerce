package com.ecommerce.main.dto;
import com.ecommerce.main.model.CartItem;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;


@NoArgsConstructor
@Data
public class CartDto {
    private Long id;
    private BigDecimal totalPrice;
    Set<CartItemDto> cartItems;
}
