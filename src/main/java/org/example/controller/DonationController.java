package org.example.controller;

import org.example.entity.Donation;
import org.example.service.DonationService;

import java.util.List;

public class DonationController {
    private final DonationService DONATION_SERVICE;

    public DonationController(DonationService DONATION_SERVICE) {
        this.DONATION_SERVICE = DONATION_SERVICE;
    }

    public void addDonation(Donation donation) {
        validateDonation(donation);
        DONATION_SERVICE.addDonation(donation);
    }

    public List<Donation> getAllDonations() {
        return DONATION_SERVICE.getAllDonations();
    }

    public Donation getDonationById(Long id) {
        validateId(id);
        return DONATION_SERVICE.getDonationById(id);
    }

    public void updateDonation(Donation donation) {
        validateDonation(donation);
        DONATION_SERVICE.updateDonation(donation);
    }

    public void deleteDonation(Long id) {
        validateId(id);
        DONATION_SERVICE.deleteDonation(id);
    }

    private void validateDonation(Donation donation) {
        if (donation == null) {
            throw new IllegalArgumentException("Donation cannot be null.");
        }

        if (donation.getCreatedAt() == null) {
            throw new IllegalArgumentException("Donation date cannot be null.");
        }
    }

    private void validateId(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null.");
        }
    }
}