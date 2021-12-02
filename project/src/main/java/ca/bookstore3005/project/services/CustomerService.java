package ca.bookstore3005.project.services;

import org.springframework.stereotype.Service;

import ca.bookstore3005.project.forms.UserForm;
import ca.bookstore3005.project.models.Customer;
import ca.bookstore3005.project.repositories.CustomerRepository;

@Service
public class CustomerService {

  CustomerRepository customerRepository;

  CustomerService(CustomerRepository customerRepository) {
    this.customerRepository = customerRepository;
  }

  public Customer getCustomer(UserForm userForm) {
    return customerRepository.findByCreds(userForm.getEmail(), userForm.getPassword());
  }

  public Customer getCustomer(String email) {
    return customerRepository.findByEmail(email);
  }

  public void addCustomer(Customer customerForm) {
    customerRepository.addCustomer(customerForm.getName(),
                                   customerForm.getEmail(), 
                                   customerForm.getPassword(),
                                   customerForm.getPhone(),
                                   customerForm.getAddress_street_num(),
                                   customerForm.getAddress_street_name(),
                                   customerForm.getAddress_street_postal(),
                                   customerForm.getCard_number(),
                                   customerForm.getAdmin());
  }
    
}
