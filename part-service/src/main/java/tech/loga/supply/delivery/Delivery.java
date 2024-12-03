package tech.loga.supply.delivery;

import jakarta.persistence.*;
import lombok.*;
import tech.loga.supply.Article;
import tech.loga.supply.order.Order;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

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

    @ManyToOne(targetEntity = Order.class, fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "order", referencedColumnName = "id")
    private Order order;

    @OneToMany(targetEntity = Article.class, fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "article", referencedColumnName = "id")
    private List<Article> articles;

    @Column(name = "delivered_at")
    private Date deliveredAt;
}
