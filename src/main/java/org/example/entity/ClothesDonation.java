package org.example.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.Date;

@Entity
@DiscriminatorValue("Roupa")
public class ClothesDonation extends Donation {
    private String description;
    private String size;

    public ClothesDonation() {
    }

    public ClothesDonation(String description, String size, double amount, Date date) {
        this.description = description;
        this.size = size;
        this.amount = amount;
        this.createdAt = date;
    }
}
