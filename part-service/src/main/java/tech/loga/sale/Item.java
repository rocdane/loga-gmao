package tech.loga.sale;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Data
@Entity
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "item")
public class Item implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product")
    private String product;

    @Column(name = "price")
    private Integer price;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "amount")
    private Integer amount;

    public void setAmount(){
        if(this.price!=0 && this.quantity!=0)
            this.amount = this.price * this.quantity;
        this.amount = 0;
    }

    public Integer getAmount(){
        if(this.price!=0 && this.quantity!=0)
            return this.price * this.quantity;
        return 0;
    }
}