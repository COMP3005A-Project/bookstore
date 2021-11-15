package ca.bookstore3005.project.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ca.bookstore3005.project.models.Book;
import ca.bookstore3005.project.services.BookService;

@Controller
public class BookController {

  private BookService bookService;

  BookController(BookService bookService) {
    this.bookService = bookService;
  }

  @RequestMapping(value = "/books", method = RequestMethod.GET)
  public String books(Model model) {

    List<Book> books = bookService.getAllBooks();

    model.addAttribute("allBooks", books);
    model.addAttribute("module", "shop");
    model.addAttribute("isAuthenticated", true);
    
    return "books";
  }
}

