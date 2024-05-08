package tech.loga.employee;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "contract")
public class Contract implements Serializable
{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "reference", unique = true, length = 24)
    private String reference;

    @Column(name = "position")
    private String position;

    @Column(name = "leaves")
    private Integer leaves;

    @Column(name = "salary")
    private Double salary;

    @Column(name = "start_at")
    private Date startAt;

    @Column(name = "end_at")
    private Date endAt;
}
