package ca.bookstore3005.project.controllers;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ca.bookstore3005.project.models.BankAccount;
import ca.bookstore3005.project.models.Publisher;
import ca.bookstore3005.project.models.ReportEntry;
import ca.bookstore3005.project.services.BankAccountService;
import ca.bookstore3005.project.services.PublisherService;
import ca.bookstore3005.project.services.ReportService;

@Controller
public class ReportController {

  Logger logger = LoggerFactory.getLogger(this.getClass());

  @Autowired
  private PublisherService publisherService;
  @Autowired
  private ReportService reportService;
  

  @GetMapping("/reports")
  public String reports(Model model) {

    List<Publisher> publishers = publisherService.getAllPublishers();

    model.addAttribute("publishers", publishers);
    

    return "reports";
  }

  @GetMapping("/report")
    public String report(@RequestParam String publisher, Model model) {
        // Get publisher data for Sales vs Expenditures
        BankAccount publisherBankInfo = publisherService.getPublisherBankAccount(publisher);

        // Get reports for Authors and Genres vs Sales 
        List<ReportEntry> authorSalesReport = reportService.getAuthorsVsSalesReport(publisher);
        List<ReportEntry> genresSalesReport = reportService.getGenresVsSalesReport(publisher);

        model.addAttribute("publisherName", publisher);
        model.addAttribute("publisher", publisherBankInfo);
        model.addAttribute("margin", publisherBankInfo.getAmount() - publisherBankInfo.getDebt_amount());
        model.addAttribute("ratio", publisherBankInfo.getAmount() / publisherBankInfo.getDebt_amount());
        model.addAttribute("authorReport", authorSalesReport);
        model.addAttribute("genreReport", genresSalesReport);

        return "report";
    }
}
