package tech.loga.reception;

import java.io.Serializable;

@Data
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "notice")
public class Notice implements Serializable
{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "code")
    private String code;

    @Column(name = "checkpoint")
    private String checkpoint;

    @Column(name = "status")
    private String status;
}
