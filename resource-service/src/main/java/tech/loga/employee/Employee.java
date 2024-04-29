package tech.loga.employee;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "employee")
public class Employee implements Serializable
{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "phone", unique = true)
    private String phone;

    @Column(name = "address")
    private String address;

    @Column(name = "birthdate")
    private Date birthdate;

    @Column(name = "picture")
    private String picture;

    @OneToMany(targetEntity = Contract.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "employee", referencedColumnName = "id")
    private List<Contract> contracts = new ArrayList<>();
}
