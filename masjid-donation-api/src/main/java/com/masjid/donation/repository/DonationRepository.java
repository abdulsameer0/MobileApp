package com.masjid.donation.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.masjid.donation.entity.Donation;

public interface DonationRepository extends JpaRepository<Donation, Long> {
    List<Donation> findByUserId(Long userId);
    
    
    @Query("SELECT COALESCE(SUM(d.amount), 0) FROM Donation d")
    Double getTotalDonations();
}

