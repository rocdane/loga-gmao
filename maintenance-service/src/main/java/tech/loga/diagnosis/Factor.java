package tech.loga.diagnosis;

import java.io.Serializable;

@Data
@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "factor")
public class Factor implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "entity")
    private String entity;

    @Column(name = "dysfunction")
    private String dysfunction;

    @Column(name = "maintenance")
    private String maintenance;

    public Factor(String entity, String dysfunction, String maintenance){
        this.entity=entity;
        this.dysfunction=dysfunction;
        this.maintenance=maintenance;
    }
}
