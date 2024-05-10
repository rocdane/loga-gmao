package tech.loga.invoicing;

import jakarta.persistence.*;
import lombok.*;
import tech.loga.payment.Payment;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "invoice")
public class Invoice implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "reference", length = 24, unique = true)
    protected String reference;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "dossier")
    private String dossier;

    @OneToMany(targetEntity = Item.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "invoice", referencedColumnName = "id")
    private List<Item> items = new ArrayList<>();

    @OneToMany(targetEntity = Payment.class, cascade=CascadeType.ALL)
    @JoinColumn(name = "invoice", referencedColumnName = "id")
    private List<Payment> payments = new ArrayList<>();

    public void addItem(Item item){
        items.add(item);
    }

    public void addPayment(Payment payment){
        payments.add(payment);
    }
}
