package ca.bookstore3005.project.controllers;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    public RedirectView login(@Validated @ModelAttribute("user") UserForm userForm, 
                              BindingResult result, RedirectAttributes attributes, HttpSession session) {
                                
      if (result.hasErrors()) {
        return new RedirectView("/");
      }

      Customer customer = customerService.getCustomer(userForm);

      if (customer != null) {
        session.setAttribute("user_email", customer.getEmail());
        return new RedirectView("/books");
      } else {
        attributes.addFlashAttribute("incorrect_credentials", true);
        return new RedirectView("/");
      }
    }
    
}