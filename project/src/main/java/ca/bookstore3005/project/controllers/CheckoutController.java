package ca.bookstore3005.project.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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

