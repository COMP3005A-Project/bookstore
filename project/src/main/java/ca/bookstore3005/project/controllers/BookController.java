package ca.bookstore3005.project.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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

  /**
   * Post method that updates the user's session cart.
   * 
   * @param isbnData A list of IsbnPacket objects that will be compiled by Spring from a JSON object
   * @param session The user session, which holds the user's cart
   * @return
   */
  @PostMapping("/updateCart")
  public ResponseEntity<String> updateCart(@RequestBody List<IsbnPacket> isbnData, HttpSession session) {
    @SuppressWarnings("unchecked")
    List<String> cart = (List<String>) session.getAttribute("cart");

    if (cart == null) {
      cart = new ArrayList<>();

      for (IsbnPacket isbnWrap : isbnData) {
        cart.add(isbnWrap.getIsbn());
      }
    
    } else {

      //Add all new unique ISBNs to the cart
      for (IsbnPacket isbnWrap : isbnData) {
        String isbn = isbnWrap.getIsbn();
        if (!cart.contains(isbn)) {
          cart.add(isbn);
        }
      }
    }

    //update session cart
    session.setAttribute("cart", cart);

    //return the number of books in cart
    return new ResponseEntity<>(String.valueOf(cart.size()), HttpStatus.OK);
  }
}
