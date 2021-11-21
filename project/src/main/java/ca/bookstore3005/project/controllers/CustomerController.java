package ca.bookstore3005.project.controllers;

import javax.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.List;

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

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import ca.bookstore3005.project.models.IsbnPacket;
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
  
    @GetMapping(value={"/", "login-page"})
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
        session.setAttribute("is_admin", customer.getAdmin());
        return new RedirectView("/books");
      } else {
        attributes.addFlashAttribute("incorrect_credentials", true);
        return new RedirectView("/");
      }
    }

  /**
   * Post method that adds books to user's cart
   * 
   * @param isbnData A list of IsbnPacket objects that will be compiled by Spring
   *                 from a JSON object
   * @param session  The user session, which holds the user's cart
   * @return
   */
  @PostMapping("/addToCart")
  public ResponseEntity<String> addToCart(@RequestBody List<IsbnPacket> isbnData, HttpSession session) {
    @SuppressWarnings("unchecked")
    List<String> cart = (List<String>) session.getAttribute("cart");

    if (cart == null) {
      cart = new ArrayList<>();

      for (IsbnPacket isbnWrap : isbnData) {
        cart.add(isbnWrap.getIsbn());
      }

    } else {

      // Add all new unique ISBNs to the cart
      for (IsbnPacket isbnWrap : isbnData) {
        String isbn = isbnWrap.getIsbn();
        if (!cart.contains(isbn)) {
          cart.add(isbn);
        }
      }
    }

    // update session cart
    session.setAttribute("cart", cart);

    // return the number of books in cart
    return new ResponseEntity<>(String.valueOf(cart.size()), HttpStatus.OK);
  }

  /**
   * Post method that removes books from user's cart
   * 
   * @param isbnData A list of IsbnPacket objects that will be compiled by Spring
   *                 from a JSON object
   * @param session  The user session, which holds the user's cart
   * @return
   */
  @PostMapping("/removeFromCart")
  public ResponseEntity<String> removeFromCart(@RequestBody List<IsbnPacket> isbnData, HttpSession session) {
    @SuppressWarnings("unchecked")
    List<String> cart = (List<String>) session.getAttribute("cart");

    // Remove each book ISBN from current cart for the session 
    for (IsbnPacket isbnWrap : isbnData) {
      String isbn = isbnWrap.getIsbn();
      cart.remove(isbn);
    }

    // update session cart
    session.setAttribute("cart", cart);

    // return the number of books in cart
    return new ResponseEntity<>(HttpStatus.OK);
  }

    @PostMapping("/logout")
    public RedirectView logout(HttpSession session) {
      
      //reset the session
      session.removeAttribute("cart");
      session.removeAttribute("user_email");
      session.removeAttribute("is_admin");

      return new RedirectView("/");
    }

    @GetMapping(value = {"/registration", "register"})
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