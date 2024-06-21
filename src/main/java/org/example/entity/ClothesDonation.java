package org.example.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Roupa")
public class ClothesDonation extends Donation {
    private String description;
    private String size;

    public ClothesDonation() {
    }

    public ClothesDonation(String description, String size, double amount) {
        this.description = description;
        this.size = size;
        this.amount = amount;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setSize(String size) {
        this.size = size;
    }
}
