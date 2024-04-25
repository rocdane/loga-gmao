package tech.loga.quality;

import java.io.Serializable;

@Data
@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@Table(name = "control")
public class Control implements Serializable
{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "code")
    private String code;

    @Column(name = "checkpoint")
    private String checkpoint;

    @Column(name = "status")
    private String status;
}
