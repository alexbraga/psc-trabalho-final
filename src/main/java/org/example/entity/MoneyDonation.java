package org.example.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Dinheiro")
public class MoneyDonation extends Donation {
    public MoneyDonation() {
    }

    public MoneyDonation(double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Doação de Dinheiro - Valor: R$" + this.getAmount() + " - Data: " + this.getCreatedAt();
    }
}
