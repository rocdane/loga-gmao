package tech.loga.repair;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "repair")
public class Repair implements Serializable
{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name="dossier", nullable = false)
    private String dossier;

    @Column(name="profile")
    private String profile;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "reference", length = 50, unique = true)
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
