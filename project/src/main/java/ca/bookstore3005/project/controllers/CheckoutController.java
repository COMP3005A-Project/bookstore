package ca.bookstore3005.project.controllers;

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

    List<Book> books = bookService.getBooksByISBN(cart);

    model.addAttribute("booksInCart", books);
    model.addAttribute("module", "cart");
    model.addAttribute("cartTotal", calculateTotalCost(books));
    
    return "cart";
  }

  @GetMapping("/checkout")
  public String checkout(Model model, HttpSession session) {
    // Get books in current cart via session
    @SuppressWarnings("unchecked")
    List<String> cart = (List<String>) session.getAttribute("cart");

    List<Book> books = bookService.getBooksByISBN(cart);

    // Get cart total
    float total = calculateTotalCost(books);

    // Get user info via session
    String customerEmail = (String) session.getAttribute("user_email");
    Customer user = customerService.getCustomer(customerEmail);

    model.addAttribute("user", user);
    model.addAttribute("total", total);
    model.addAttribute("module", "checkout");
    model.addAttribute("isAuthenticated", true);
    
    return "checkout";
  }

  /**
   * Function to tally up the prices of books in cart to get a total cost...
   * 
   * @param books List of books objects to that are in the cart
   * @return Sum of all prices of books in cart (i.e., total)
   */
  private static float calculateTotalCost(List<Book> books) {
    float total = 0;
    for (Book book : books) {
      total += book.getPrice();
    }

    return total;
  }
}

