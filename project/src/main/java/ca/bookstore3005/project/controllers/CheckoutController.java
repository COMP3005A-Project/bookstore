package ca.bookstore3005.project.controllers;

import java.util.List;
import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import ca.bookstore3005.project.models.Book;
import ca.bookstore3005.project.services.BookService;

@Controller
public class CheckoutController {

  private BookService bookService;

  CheckoutController(BookService bookService) {
    this.bookService = bookService;
  }

  @GetMapping("/cart")
  public String cart(Model model) {

    // TODO: Need to grab from spring session
    List<String> isbns = new ArrayList<>();
    
    // TODO: Pass in list of ISBN's from spring session...
    List<Book> books = bookService.getBooksByISBN(isbns);

    model.addAttribute("booksInCart", books);
    model.addAttribute("module", "cart");
    model.addAttribute("isAuthenticated", true);
    
    return "cart";
  }

  @GetMapping("/checkout")
  public String checkout(Model model) {

    // TODO: Need to grab from spring session
    List<String> isbns = new ArrayList<>();
    
    // TODO: Pass in list of ISBN's from spring session...
    List<Book> books = bookService.getBooksByISBN(isbns);

    model.addAttribute("booksInCart", books);
    model.addAttribute("module", "checkout");
    model.addAttribute("isAuthenticated", true);
    
    return "checkout";
  }
}

