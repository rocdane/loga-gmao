package tech.loga.quality;

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
@Table(name = "quality")
public class Quality implements Serializable
{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "dossier", nullable = false)
    private String dossier;

    @Column(name = "profile")
    private String profile;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "reference", length = 50, unique = true)
    private String reference;

    @Column(name = "mileage")
    private Integer mileage;

    @Column(name = "comment")
    private String comment;

    @OneToMany(targetEntity = Control.class, cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "quality", referencedColumnName = "id")
    private List<Control> controls = new ArrayList<>();

    public void addControl(Control control){
        this.controls.add(control);
    }
}
