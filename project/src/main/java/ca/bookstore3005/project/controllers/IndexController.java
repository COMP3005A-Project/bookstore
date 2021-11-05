package ca.bookstore3005.project.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ca.bookstore3005.project.models.Book;

@Controller
public class IndexController {

  @RequestMapping(value = "/", method = RequestMethod.GET)
  public String index(Model model) {

    Book book = new Book("testText");
    model.addAttribute("book", book);
    
    return "index";
  }
}

