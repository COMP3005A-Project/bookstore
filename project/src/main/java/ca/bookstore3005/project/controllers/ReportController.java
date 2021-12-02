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
public class ReportController {

  Logger logger = LoggerFactory.getLogger(this.getClass());

  @Autowired
  private PublisherService publisherService;  
  

  @GetMapping("/reports")
  public String books(Model model) {

    List<Publisher> publishers = publisherService.getAllPublishers();

    model.addAttribute("publishers", publishers);
    

    return "reports";
  }
}
