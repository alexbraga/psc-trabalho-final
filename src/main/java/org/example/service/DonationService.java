package org.example.service;

import org.example.dao.DonationDAO;
import org.example.entity.ClothesDonation;
import org.example.entity.Donation;
import org.example.entity.FoodDonation;
import org.example.entity.MoneyDonation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    public Map<String, Double> getTotalDonationsByType() {
        List<Donation> donationList = DONATION_DAO.getAllDonations();

        Map<String, Double> totals = new HashMap<>();
        totals.put("Alimentos", 0.0);
        totals.put("Roupas", 0.0);
        totals.put("Dinheiro", 0.0);

        for (Donation donation : donationList) {
            if (donation instanceof FoodDonation) {
                totals.put("Alimentos", totals.get("Alimentos") + donation.getAmount());
            } else if (donation instanceof ClothesDonation) {
                totals.put("Roupas", totals.get("Roupas") + donation.getAmount());
            } else if (donation instanceof MoneyDonation) {
                totals.put("Dinheiro", totals.get("Dinheiro") + donation.getAmount());
            }
        }

        return totals;
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
