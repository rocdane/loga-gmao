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
@Table(name = "delivery")
public class Delivery implements Serializable
{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(targetEntity = Furnisher.class, fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "furnisher", referencedColumnName="id" )
    private Furnisher furnisher;

    @OneToMany(targetEntity = Article.class, cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "delivery", referencedColumnName = "id")
    private List<Article> articles = new ArrayList<>();

    @Column(name = "delivered_at")
    private Date deliveredAt;

    public void addArticle(Article article){
        this.articles.add(article);
    }
}
