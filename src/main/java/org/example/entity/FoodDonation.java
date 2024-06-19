package org.example.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.Date;

@Entity
@DiscriminatorValue("Alimento")
public class FoodDonation extends Donation {
    public FoodDonation() {
    }

    public FoodDonation(double amount, Date date) {
        this.amount = amount;
        this.createdAt = date;
    }
}
