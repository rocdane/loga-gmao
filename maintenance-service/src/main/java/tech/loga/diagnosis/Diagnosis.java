package tech.loga.diagnosis;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "diagnosis")
public class Diagnosis implements Serializable{

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "profile")
    private String profile;

    @Column(name = "dossier", nullable = false)
    private String dossier;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "reference", length = 50, unique = true)
    private String reference;

    @Column(name = "mileage")
    private Integer mileage;

    @Column(name = "description")
    private String description;

    @OneToMany(targetEntity = Factor.class, cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "diagnosis", referencedColumnName = "id")
    private List<Factor> factors = new ArrayList<>();

    public void addFactor(Factor factor){
        this.factors.add(factor);
    }
}
