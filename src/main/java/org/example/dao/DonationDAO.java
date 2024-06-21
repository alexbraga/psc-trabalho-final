package org.example.dao;

import org.example.entity.Donation;

import java.util.List;

public interface DonationDAO {
    void addDonation(Donation donation);
    List<Donation> getAllDonations();
    Donation getDonationById(Long id);
    void updateDonation(Donation donation);
    void deleteDonation(Long id);
}
