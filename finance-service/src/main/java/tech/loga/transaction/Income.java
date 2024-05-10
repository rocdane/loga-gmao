package tech.loga.transaction;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@DiscriminatorValue("INCOME")
public class Income extends Transaction implements Serializable {
    @Column(name = "income")
    private String income;
}
