package tech.loga.supply;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Data
@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "furnisher")
public class Furnisher implements Serializable
{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name",unique = true)
    private String name;

    @Column(name = "address")
    private String address;

    @Column(name = "contact",unique = true)
    private String contact;
}
