package tech.loga.company;

import jakarta.persistence.*;
import lombok.*;
import tech.loga.asset.Asset;
import tech.loga.employee.Employee;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "department")
public class Department implements Serializable
{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "reference", length = 24, unique = true)
    private String reference;

    @OneToMany(targetEntity = Asset.class, cascade={CascadeType.PERSIST, CascadeType.REMOVE}, fetch = FetchType.LAZY)
    @JoinColumn(name = "department", referencedColumnName = "id")
    private List<Asset> assets = new ArrayList<>();

    @OneToMany(targetEntity = Employee.class, cascade={CascadeType.PERSIST, CascadeType.REMOVE}, fetch = FetchType.LAZY)
    @JoinColumn(name = "department", referencedColumnName = "id")
    private List<Employee> employees = new ArrayList<>();
}
