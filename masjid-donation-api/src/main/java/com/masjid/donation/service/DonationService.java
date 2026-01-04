package com.masjid.donation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masjid.donation.entity.Donation;
import com.masjid.donation.entity.DonationType;
import com.masjid.donation.entity.User;
import com.masjid.donation.repository.DonationRepository;
import com.masjid.donation.repository.DonationTypeRepository;
import com.masjid.donation.repository.UserRepository;

@Service
public class DonationService {

    @Autowired
    private DonationRepository donationRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DonationTypeRepository donationTypeRepository;

    public String donate(Long userId, Long typeId, Double amount, String notes){

        User user = userRepository.findById(userId).orElseThrow();
        DonationType type = donationTypeRepository.findById(typeId).orElseThrow();

        Donation d = new Donation();
        d.setUser(user);
        d.setDonationType(type);
        d.setAmount(amount);
        d.setNotes(notes);

        donationRepository.save(d);

        return "Donation saved successfully";
    }
    
    public Double getTotalDonationAmount(){

        return donationRepository.getTotalDonations();
    }

}

