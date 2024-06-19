package org.example.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.Date;

@Entity
@DiscriminatorValue("Dinheiro")
public class MoneyDonation extends Donation {
    public MoneyDonation() {
    }

    public MoneyDonation(double amount, Date date) {
        this.amount = amount;
        this.createdAt = date;
    }
}
