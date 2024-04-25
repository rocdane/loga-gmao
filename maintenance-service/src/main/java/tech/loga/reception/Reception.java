package tech.loga.reception;

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
@Table(name = "reception")
@ToString
public class Reception implements Serializable
{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "dossier", nullable = false)
    private String dossier;

    @Column(name = "profile")
    private String profile;

    @Column(name = "reference", length = 50, unique = true)
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

    public void addNotice(Notice notice){
        this.notices.add(notice);
    }
}
