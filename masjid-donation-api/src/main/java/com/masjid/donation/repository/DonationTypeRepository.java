package com.masjid.donation.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masjid.donation.entity.DonationType;

public interface DonationTypeRepository extends JpaRepository<DonationType, Long> {
	
}
