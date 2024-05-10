package tech.loga.payment;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "payment")
public class Payment implements Serializable
{

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "closed_at")
    private Date closedAt;

    @Column(name = "mode")
    private String mode;

    @Column(name = "reference")
    private String reference;

    @Column(name = "amount")
    private Integer amount;
}
