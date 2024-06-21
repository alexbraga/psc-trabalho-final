package org.example.service;

import org.example.dao.DonationDAO;
import org.example.entity.Donation;

import java.util.List;
import java.util.Optional;

public class DonationService {
    private final DonationDAO DONATION_DAO;

    public DonationService(DonationDAO donationDAO) {
        this.DONATION_DAO = donationDAO;
    }

    public void addDonation(Donation donation) {
        validateDonation(donation);
        DONATION_DAO.addDonation(donation);
    }

    public List<Donation> getAllDonations() {
        return DONATION_DAO.getAllDonations();
    }

    public Donation getDonationById(Long id) {
        return  DONATION_DAO.getDonationById(id);
    }

    public void updateDonation(Donation donation) {
        validateDonation(donation);

        Optional<Donation> existingDonation = Optional.ofNullable(DONATION_DAO.getDonationById(donation.getId()));

        if (existingDonation.isPresent()) {
            DONATION_DAO.updateDonation(donation);
        } else {
            throw new IllegalArgumentException("Donation with id " + donation.getId() + " does not exist.");
        }
    }

    public void deleteDonation(Long id) {
        Optional<Donation> existingDonation = Optional.ofNullable(DONATION_DAO.getDonationById(id));

        if (existingDonation.isPresent()) {
            DONATION_DAO.deleteDonation(id);
        } else {
            throw new IllegalArgumentException("Donation with id " + id + " does not exist.");
        }
    }

    private void validateDonation(Donation donation) {
        if (donation.getAmount() <= 0) {
            throw new IllegalArgumentException("Donation amount must be positive.");
        }
    }
}
