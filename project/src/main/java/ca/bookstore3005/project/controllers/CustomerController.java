package ca.bookstore3005.project.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ca.bookstore3005.project.models.Customer;
import ca.bookstore3005.project.services.CustomerService;

@Controller
public class CustomerController {
    private CustomerService customerService;

    CustomerController(CustomerService customerService) {
      this.customerService = customerService;
    }
  
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String login(Model model) {
        
      //List<Book> books = bookService.getAllBooks();

      
      return "login";
    }
}
