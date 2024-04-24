package tech.loga.client;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "client")
public class Client implements Serializable
{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private String type;

    @Column(name = "legal")
    private String legal;

    @Column(name = "address")
    private String address;

    @Column(name = "contact",unique = true)
    private String contact;

    public Client(String name,
                  String type,
                  String legal,
                  String address,
                  String contact){
        this.name=name;
        this.type=type;
        this.legal=legal;
        this.address=address;
        this.contact=contact;
    }
}
