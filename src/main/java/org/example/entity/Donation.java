package org.example.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type")
@Table(name = "donation")
public class Donation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;
    protected double amount;
    @Temporal(TemporalType.TIMESTAMP)
    protected Date createdAt = new Date();

    public String getDonationType() {
        return this.getClass().getSimpleName();
    }

    public double getAmount() {
        return amount;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setCreatedAt(Date date) {
        this.createdAt = date;
    }
}