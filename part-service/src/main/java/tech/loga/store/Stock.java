package tech.loga.store;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Stock implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(targetEntity = Product.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "product", referencedColumnName = "id")
    private Product product;

    @Column(name = "quantity")
    private Integer quantity;

    public void increase(int quantity){
        this.setQuantity(this.getQuantity()+quantity);
    }

    public void decrease(int quantity){
        if(this.quantity.compareTo(quantity) > 0){
            this.setQuantity(this.getQuantity()-quantity);
        }
        throw new IllegalArgumentException(String.format("%d greater than stock.",id));
    }
}
