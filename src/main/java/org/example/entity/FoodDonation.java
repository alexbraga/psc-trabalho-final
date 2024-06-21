package org.example.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Alimento")
public class FoodDonation extends Donation {
    public FoodDonation() {
    }

    public FoodDonation(double amount) {
        this.amount = amount;
    }
}
