package ca.bookstore3005.project.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ReportsController {
  
  @GetMapping("/reports")
  public String reports(Model model) {

    model.addAttribute("module", "reports");

    return "reports";
  }

}
