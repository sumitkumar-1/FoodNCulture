package com.dalhousie.server.controller;

import com.dalhousie.server.contract.IDonationRepository;
import com.dalhousie.server.model.Donation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/donations")
public class DonationController {

    @Autowired
    private IDonationRepository donationRepository;
    
    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> createDonation(@RequestBody Donation donation) {
        if(donationRepository.save(donation) > 0) {
            return new ResponseEntity<>("Donation created successfully", HttpStatus.CREATED);
        }else{
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/")
    public List<Donation> getAllDonation(){
        return donationRepository.findAll();
    }

    @GetMapping("/events/{id}")
    public List<Donation> getAllEventDonations(@PathVariable Integer id){
        return donationRepository.getDonationsByEventId(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Donation> get(@PathVariable Integer id) {
        return donationRepository.getById(id)
        .map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateDonation(@PathVariable Integer id, @RequestBody Donation donation) {
        return donationRepository.getById(id)
        .map(savedDonation -> {
            savedDonation.setEventId(donation.getEventId());
            savedDonation.setName(donation.getName());
            savedDonation.setAmount(donation.getAmount());
            savedDonation.setEmail(donation.getEmail());
            savedDonation.setNote(donation.getNote());
            savedDonation.setUpdatedAt(donation.getUpdatedAt());
            savedDonation.setCreatedAt(donation.getCreatedAt());

            donationRepository.update(savedDonation);
            return new ResponseEntity<>("Donation updated successfully", HttpStatus.OK);
        })
        .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) {
        if(donationRepository.deleteById(id) > 0) {
            return new ResponseEntity<>("Donation deleted successfully", HttpStatus.OK);
        }else{
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/total/{id}")
    public double getTotalDonationByEventId(@PathVariable Integer id) {
        return donationRepository.getTotalDonationByEventId(id);
    }
}
