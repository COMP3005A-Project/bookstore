package ca.bookstore3005.project.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ca.bookstore3005.project.models.Book;
import ca.bookstore3005.project.services.BookService;

@Controller
public class BookController {

  Logger logger = LoggerFactory.getLogger(this.getClass());

  private BookService bookService;

  BookController(BookService bookService) {
    this.bookService = bookService;
  }

  @GetMapping("/books")
  public String books(Model model, HttpSession session) {

    List<Book> books = bookService.getAllBooks();

    model.addAttribute("allBooks", books);
    model.addAttribute("module", "shop");
    model.addAttribute("isAuthenticated", true);

    @SuppressWarnings("unchecked")
    List<String> cart = (List<String>) session.getAttribute("cart");
    if (cart != null) {
      model.addAttribute("num_in_cart", String.format("(%s)", String.valueOf(cart.size())));
    } else {
      model.addAttribute("num_in_cart", "");
    }

    return "books";
  }

  @GetMapping("/book")
  public String book(@RequestParam String isbn, Model model) {

    model.addAttribute("book", bookService.getBookByISBN(isbn));

    return "book";
  }

}
