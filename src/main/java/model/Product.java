package model;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Set;
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
    private String name;
    private String brand;
    private BigDecimal price;
    private int inventory;

    @ManyToOne
    @JoinColumn(name="category-id",referencedColumnName = "id")
    private Category category;

    @OneToMany(cascade = CascadeType.ALL)
    Set<Image> images;


}
