package ca.bookstore3005.project.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import ca.bookstore3005.project.models.Book;
import ca.bookstore3005.project.models.IsbnPacket;
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


  @PostMapping("/removeBooks")
  public ResponseEntity<String> removeBooks(@RequestBody List<IsbnPacket> isbnData, HttpSession session) {
    Boolean is_admin = (Boolean) session.getAttribute("is_admin");

    if (is_admin) {
      List<String> ISBNs = isbnData.stream().map(isbn -> isbn.getIsbn()).collect(Collectors.toList());

      bookService.deleteBooks(ISBNs);
    }

    return new ResponseEntity<>(HttpStatus.OK);
  }
}
