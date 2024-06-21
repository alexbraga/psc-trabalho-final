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

    @Override
    public String toString() {
        return "Doação de Alimento - Quantidade: " + this.getAmount() + " Kg/L" + " - Data: " + this.getCreatedAt();
    }
}
