package ca.bookstore3005.project.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

  @RequestMapping("/")
  public String index() {

    return "Hello World";
  }
}

