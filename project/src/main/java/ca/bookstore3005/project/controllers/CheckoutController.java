package ca.bookstore3005.project.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ca.bookstore3005.project.models.Book;
import ca.bookstore3005.project.models.Customer;
import ca.bookstore3005.project.services.BookService;
import ca.bookstore3005.project.services.CustomerService;

@Controller
public class CheckoutController {

  private BookService bookService;
  private CustomerService customerService;

  CheckoutController(BookService bookService, CustomerService customerService) {
    this.bookService = bookService;
    this.customerService = customerService;
  }

  @GetMapping("/cart")
  public String cart(Model model, HttpSession session) {
    // Get books in current cart via session
    @SuppressWarnings("unchecked")
    List<String> cart = (List<String>) session.getAttribute("cart");

    

    List<Book> books = new ArrayList<>();
    if (cart != null) {
      books = bookService.getBooksByISBN(cart);
    }

    model.addAttribute("booksInCart", books);
    model.addAttribute("module", "cart");
    model.addAttribute("cartTotal", bookService.calculateTotalCost(books));
    
    return "cart";
  }

  @GetMapping("/checkout")
  public String checkout(Model model, HttpSession session) {
    // Get books in current cart via session
    @SuppressWarnings("unchecked")
    List<String> cart = (List<String>) session.getAttribute("cart");

    model.addAttribute("module", "checkout");

    if (session.getAttribute("user_email") == null) {
      model.addAttribute("reason", "Not Logged In...");
      model.addAttribute("message", "You are currently no logged in. Please login and add items to your cart before checking out.");
      model.addAttribute("buttonText", "Login");
      model.addAttribute("buttonHref", "login-page");

      return "invalid_action";
    } else if (cart == null || cart.isEmpty()) {
      model.addAttribute("reason", "No items in cart...");
      model.addAttribute("message", "There are no items in your cart. Please add books to your cart before checking out.");
      model.addAttribute("buttonText", "Return To Books");
      model.addAttribute("buttonHref", "books");

      return "invalid_action";
    } 

    // Get books from the cart
    List<Book> books = bookService.getBooksByISBN(cart);

    // Get user info via session
    String customerEmail = (String) session.getAttribute("user_email");
    Customer user = customerService.getCustomer(customerEmail);

    model.addAttribute("user", user);
    model.addAttribute("booksInCart", books);
    model.addAttribute("total", bookService.calculateTotalCost(books));

    model.addAttribute("module", "checkout");

    
    return "checkout";
  }
}

