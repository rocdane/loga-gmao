package tech.loga.sale;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "sale")
public class Sale implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "reference", length = 24, unique = true)
    private String reference;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "updated_at")
    private Date updatedAt;

    @Column(name = "customer")
    private String customer;

    @OneToMany(targetEntity = Item.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="sale", referencedColumnName="id")
    private List<Item> items = new ArrayList<>();
}
