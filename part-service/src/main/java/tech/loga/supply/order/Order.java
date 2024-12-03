package tech.loga.supply.order;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tech.loga.supplier.Supplier;
import tech.loga.supply.Article;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Order implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne(targetEntity = Supplier.class, fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "supplier", referencedColumnName="id")
    private Supplier supplier;

    @OneToMany(targetEntity = Article.class, fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "article", referencedColumnName = "id")
    private List<Article> articles;

    @Column(name = "ordered_at")
    private Date orderedAt;
}
