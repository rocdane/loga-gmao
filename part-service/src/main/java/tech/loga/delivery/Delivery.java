package tech.loga.delivery;

import jakarta.persistence.*;
import lombok.*;
import tech.loga.order.Order;
import tech.loga.supplier.Supplier;

import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "delivery")
public class Delivery implements Serializable
{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne(targetEntity = Supplier.class, fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "supplier", referencedColumnName="id")
    private Supplier supplier;

    @ManyToOne(targetEntity = Order.class, fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "supply", referencedColumnName = "id")
    private Order order;

    @Column(name = "created_at")
    private Date createdAt;
}
