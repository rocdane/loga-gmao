package tech.loga.asset;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "asset")
public class Asset implements Serializable
{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "reference", length = 24, unique = true)
    private String reference;

    @Column(name = "designation")
    private String designation;

    @Column(name = "value")
    private Double value;

    @Column(name = "entry_on")
    private Date entryOn;
}
