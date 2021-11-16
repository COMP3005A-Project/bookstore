package ca.bookstore3005.project.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import ca.bookstore3005.project.forms.UserForm;
import ca.bookstore3005.project.models.Customer;
import ca.bookstore3005.project.services.CustomerService;

@Controller
public class CustomerController {
    private CustomerService customerService;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    CustomerController(CustomerService customerService) {
      this.customerService = customerService;
    }
  
    @GetMapping("/")
    public ModelAndView loginView(Model model) {

      return new ModelAndView("login", "user", new UserForm());
    }
    
    @PostMapping("/login")
    public RedirectView login(@Validated @ModelAttribute("user") UserForm userForm, BindingResult result, RedirectAttributes attributes) {
      if (result.hasErrors()) {
        return new RedirectView("/");
      }

      Customer customer = customerService.getCustomer(userForm);

      if (customer != null) {
        return new RedirectView("/books");
      } else {
        attributes.addFlashAttribute("incorrect_credentials", true);
        return new RedirectView("/");
      }
    }

    @GetMapping("/registration")
    public String registration(Model model) {

      // pass a Customer to be used in form in the name of user_register
      model.addAttribute("user_register", new Customer());
      return "registration";
    }

    @PostMapping("/registration_signup")
    public RedirectView registrationView(@Validated @ModelAttribute("user_register") Customer customer) {
      
      try {
            customerService.addCustomer(customer);
          } 
      // Checks for duplicates
      catch (DataIntegrityViolationException e) {
            return new RedirectView("/registration");
          }
      
       return new RedirectView("/");
      
    }
    
}