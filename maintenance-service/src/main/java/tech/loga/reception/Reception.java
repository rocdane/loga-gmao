package tech.loga.reception;

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
@Table(name = "reception")
public class Reception implements Serializable
{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "customer", nullable = false)
    private String customer;

    @Column(name = "employee")
    private String employee;

    @Column(name = "reference", length = 24, unique = true)
    private String reference;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "mileage")
    private Integer mileage;

    @Column(name = "description")
    private String description;

    @OneToMany(targetEntity = Notice.class, cascade=CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "reception", referencedColumnName = "id")
    private List<Notice> notices = new ArrayList<>();
}
