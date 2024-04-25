package tech.loga.repair;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "repair")
public class Repair implements Serializable
{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "customer", nullable = false)
    private String customer;

    @Column(name = "employee")
    private String employee;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "reference", length = 24, unique = true)
    private String reference;

    @Column(name = "description")
    private String description;

    @Column(name = "mileage")
    private Integer mileage;

    @Column(name = "is_approved")
    private Boolean approved;

    @OneToMany(targetEntity = Task.class, cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "repair", referencedColumnName = "id")
    private List<Task> tasks = new ArrayList<>();

    @OneToMany(targetEntity = Spare.class, cascade=CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "repair", referencedColumnName = "id")
    private List<Spare> spares = new ArrayList<>();
}
