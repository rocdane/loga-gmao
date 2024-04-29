package tech.loga.company;

import jakarta.persistence.*;
import lombok.*;
import tech.loga.employee.Employee;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "company")
public class Company implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", unique = true)
    private String name;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "phone", unique = true)
    private String phone;

    @Column(name = "legal", unique = true)
    private String legal;

    @Column(name = "location")
    private String location;

    @OneToMany(targetEntity = Branch.class, cascade={CascadeType.PERSIST, CascadeType.REMOVE}, fetch = FetchType.LAZY)
    @JoinColumn(name = "company", referencedColumnName = "id")
    private List<Branch> branches = new ArrayList<>();
}

