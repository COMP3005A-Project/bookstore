package ca.bookstore3005.project.services;

import org.springframework.stereotype.Service;

import ca.bookstore3005.project.models.Customer;
import ca.bookstore3005.project.repositories.RegionRepository;

@Service
public class RegionService {

  RegionRepository regionRepository;

  RegionService(RegionRepository regionRepository) {
    this.regionRepository = regionRepository;
  }

  public void addRegion(Customer customerForm) {
    regionRepository.addRegion(customerForm.getAddress_street_postal(),
                                   customerForm.getCity(), 
                                   customerForm.getProvince().toUpperCase());
  }
    
}
