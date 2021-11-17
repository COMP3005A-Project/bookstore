package ca.bookstore3005.project.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

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
  public String cart(Model model, HttpSession session) {

    @SuppressWarnings("unchecked")
    List<String> cart = (List<String>) session.getAttribute("cart");

    List<Book> books = new ArrayList<>();
    if (cart != null) {
      books = bookService.getBooksByISBN(cart);
    }

    model.addAttribute("booksInCart", books);
    model.addAttribute("module", "cart");
    
    return "cart";
  }

  @GetMapping("/checkout")
  public String checkout(Model model) {

    model.addAttribute("module", "checkout");
    
    return "checkout";
  }
}

