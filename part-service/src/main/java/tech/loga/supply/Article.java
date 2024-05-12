package tech.loga.supply;

import jakarta.persistence.*;
import lombok.*;
import org.loga.part.product.Product;

import java.io.Serializable;

@Data
@Entity
@ToString
@Getter
@Setter
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
    private Integer price;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "amount")
    private Integer amount;

    @Column(name = "is_ordered")
    private Boolean ordered;

    @Column(name = "is_delivered")
    private Boolean delivered;

    public void setAmount(){
        if(this.price!=0 && this.quantity!=0)
            this.amount = this.price * this.quantity;
    }

    public Integer getAmount(){
        if(this.price!=0 && this.quantity!=0)
            return this.price * this.quantity;
        return 0;
    }
}