package pl.coderslab.charity.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import pl.coderslab.charity.entity.Donation;
import pl.coderslab.charity.repository.DonationRepository;

@Service
public class DonationServiceImpl implements DonationService {

    private final DonationRepository donationRepository;

    public DonationServiceImpl(DonationRepository donationRepository) {
        this.donationRepository = donationRepository;
    }
    @Override
    public void save(Donation donation) {
        donation.setCreated(LocalDateTime.now());
        donation.setReceived(false);
        donationRepository.save(donation);
    }
    
}
