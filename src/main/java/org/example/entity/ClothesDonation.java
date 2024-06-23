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

    public ClothesDonation(String description, String size) {
        this.description = description;
        this.size = size;
        this.amount = 1;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "Doação de Roupa - Descrição: " + this.getDescription() + ", Tamanho: " + this.getSize() + ", Quantidade: " + this.getAmount() + " - Data: " + this.getCreatedAt();
    }
}
