package org.example.dao;

import org.example.entity.Donation;
import org.example.util.EntityManagerFactoryProvider;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

public class DonationDAOImpl implements DonationDAO {
    private final EntityManagerFactoryProvider entityManagerFactoryProvider;

    public DonationDAOImpl(EntityManagerFactoryProvider entityManagerFactoryProvider) {
        this.entityManagerFactoryProvider = entityManagerFactoryProvider;
    }

    private EntityManager getEntityManager() {
        EntityManagerFactory emf = entityManagerFactoryProvider.getEntityManagerFactory();
        return emf.createEntityManager();
    }

    @Override
    public void addDonation(Donation donation) {
        EntityManager em = getEntityManager();

        em.getTransaction().begin();
        em.persist(donation);
        em.getTransaction().commit();

        em.close();
    }

    @Override
    public List<Donation> getAllDonations() {
        EntityManager em = getEntityManager();
        List<Donation> donationList;

        donationList = em.createQuery("FROM Donation", Donation.class).getResultList();
        em.close();

        return donationList;
    }

    @Override
    public Donation getDonationById(Long id) {
        EntityManager em = getEntityManager();

        Donation foundDonation = em.find(Donation.class, id);
        em.close();

        return foundDonation;
    }

    @Override
    public void updateDonation(Donation donation) {
        EntityManager em = getEntityManager();

        Donation existingDonation = em.find(Donation.class, donation.getId());

        if (existingDonation != null) {
            em.getTransaction().begin();
            em.merge(donation);
            em.getTransaction().commit();
        }

        em.close();
    }

    @Override
    public void deleteDonation(Long id) {
        EntityManager em = getEntityManager();

        em.getTransaction().begin();
        Donation donation = em.find(Donation.class, id);

        if (donation != null) {
            em.remove(donation);
            em.getTransaction().commit();
        }

        em.close();
    }
}