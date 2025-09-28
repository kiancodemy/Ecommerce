package com.ecommerce.main.model;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="products")
@NoArgsConstructor
@Getter
@Setter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_seq")
    @SequenceGenerator(name = "product_seq", sequenceName = "product_seq", allocationSize = 1)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String brand;

    @Column(nullable = false)
    private BigDecimal price;

    @Lob
    private String description;

    @Column(nullable = false)
    private int inventory;

    @ManyToOne
    @JoinColumn(name="category_id", referencedColumnName = "id",nullable = false)
    private Category category;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.EAGER)
    private List<Image> images = new ArrayList<>();
}