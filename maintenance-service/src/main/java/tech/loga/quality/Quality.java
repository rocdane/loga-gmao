package tech.loga.quality;

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
@Table(name = "quality")
public class Quality implements Serializable
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

    @Column(name = "mileage")
    private Integer mileage;

    @Column(name = "comment")
    private String comment;

    @OneToMany(targetEntity = Control.class, cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "quality", referencedColumnName = "id")
    private List<Control> controls = new ArrayList<>();
}
