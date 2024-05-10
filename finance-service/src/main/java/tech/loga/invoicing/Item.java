package tech.loga.invoicing;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Data
@Entity
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "item")
public class Item implements Serializable
{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "quantity")
    private Float quantity;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "tax_group")
    private String taxGroup;
}
