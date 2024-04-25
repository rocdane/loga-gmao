package tech.loga.repair;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "task")
public class Task implements Serializable
{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "description")
    private String description;

    @Column(name = "cost")
    private Integer cost;

    @Column(name = "duration")
    private Integer duration;

    public Task(String description, Integer cost) {
        this.description = description;
        this.cost = cost;
    }
}
