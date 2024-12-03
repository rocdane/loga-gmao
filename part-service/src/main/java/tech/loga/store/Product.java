package tech.loga.store;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Data
@Entity
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "product")
public class Product implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "reference", length = 24, unique = true)
    private String reference;

    @Column(name = "category")
    private String category;

    @Column(name = "brand")
    private String brand;

    @Column(name = "name")
    private String name;

    @Column(name = "details")
    private String details;

    @Column(name = "price")
    private Double price;

    @Column(name = "limit")
    private Integer limit;
}
