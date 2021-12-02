package ca.bookstore3005.project.controllers;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import ca.bookstore3005.project.models.Publisher;

import ca.bookstore3005.project.models.Book;
import ca.bookstore3005.project.models.IsbnPacket;
import ca.bookstore3005.project.services.BookService;
import ca.bookstore3005.project.services.PublisherService;

@Controller
public class BookController {

  Logger logger = LoggerFactory.getLogger(this.getClass());

  private BookService bookService;
  @Autowired
  private PublisherService publisherService;

  BookController(BookService bookService) {
    this.bookService = bookService;
  }

  @GetMapping("/books")
  public String books(Model model, HttpSession session) {

    List<Book> books = bookService.getAllBooks();

    model.addAttribute("allBooks", books);
    model.addAttribute("module", "shop");

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

  @GetMapping("/addBook")
    public String addBook(Model model) {

      List<Publisher> publishers = publisherService.getAllPublishers();
      model.addAttribute("new_book", new Book());
      model.addAttribute("allPublishers", publishers);

      return "addBook";
    }

    @PostMapping("/add_new_book")
    public RedirectView newBook(@Validated @ModelAttribute("new_book") Book book) {
      
      try {
            bookService.addBook(book);
          } 
      catch (DataIntegrityViolationException e) {
            return new RedirectView("/addBook");
          }
      
       return new RedirectView("/books");
      
    }
}
