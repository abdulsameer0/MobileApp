package com.masjid.donation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.masjid.donation.service.DonationService;

@RestController
@RequestMapping("/api/donation")
public class DonationController {

    @Autowired
    private DonationService donationService;
    
    
    @GetMapping("/")
    public String test() {
    	return "tesing api";
    }

    @PostMapping
    public String donate(@RequestParam Long userId,
                         @RequestParam Long typeId,
                         @RequestParam Double amount,
                         @RequestParam(required = false) String notes){

        return donationService.donate(userId, typeId, amount, notes);
    }
    
    @GetMapping("/total")
    public Double getTotalDonation(){
        return donationService.getTotalDonationAmount();
    }

}

