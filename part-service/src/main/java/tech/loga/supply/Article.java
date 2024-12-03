package tech.loga.supply;

import jakarta.persistence.*;
import lombok.*;
import tech.loga.store.Product;

import java.io.Serializable;

/**
 * Pour un même Article, la quantité livré n'est jamais supérieur à la quantité commndée.
 * Pour un même Article, la quantité commandé est supérieur ou égale à la quantité livrée.
 * */
@Data
@Entity
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "article")
public class Article implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne(targetEntity = Product.class, fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "product", referencedColumnName = "id")
    private Product product;

    @Column(name = "price")
    private Double price;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "is_delivered")
    private Boolean delivered;
}