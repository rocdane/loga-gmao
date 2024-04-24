package tech.loga.automobile;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "automobile")
public class Automobile implements Serializable
{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "number",unique = true)
    private String number;

    @Column(name = "vin",length = 17,unique = true)
    private String vin;

    @Column(name = "make")
    private String make;

    @Column(name = "model")
    private String model;

    @Column(name = "trim")
    private Integer trim;

    @Column(name = "unit")
    private String unit;

    public Automobile(String number,
                      String vin,
                      String make,
                      String model,
                      Integer trim,
                      String unit) {
        this.number = number;
        this.vin = vin;
        this.make = make;
        this.model = model;
        this.trim = trim;
        this.unit = unit;
    }
}
