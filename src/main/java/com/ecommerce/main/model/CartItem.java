package com.ecommerce.main.model;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name="cartitems")
@NoArgsConstructor
@Getter
@Setter
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private int quantity;
    private BigDecimal unitPrice;
    private BigDecimal totalPrice;

    @ManyToOne
    @JoinColumn(name = "product-id", referencedColumnName = "id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "cart-id", referencedColumnName = "id", nullable = false)
    private Cart cart;

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = unitPrice.multiply(new BigDecimal(quantity));
    }
}
