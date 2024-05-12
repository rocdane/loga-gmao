package tech.loga.supply;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "order")
public class Order implements Serializable
{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "reference", unique = true)
    private String reference;

    @Column(name = "ordered_at")
    private Date orderedAt;

    @OneToMany(targetEntity = Article.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "order", referencedColumnName = "id")
    private List<Article> articles = new ArrayList<>();

    @OneToMany(targetEntity = Delivery.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "order", referencedColumnName = "id")
    private List<Delivery> deliveries = new ArrayList<>();

    public void addArticle(Article article){
        this.articles.add(article);
    }

    public void addDelivery(Delivery delivery){
        this.deliveries.add(delivery);
    }
}
