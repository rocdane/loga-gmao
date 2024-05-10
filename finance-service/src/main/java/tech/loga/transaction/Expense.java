package tech.loga.transaction;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@DiscriminatorValue("EXPENSE")
public class Expense extends Transaction implements Serializable {
    @Column(name = "expense")
    private String expense;
}