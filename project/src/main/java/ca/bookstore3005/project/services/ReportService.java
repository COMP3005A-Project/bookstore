package ca.bookstore3005.project.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ca.bookstore3005.project.models.ReportEntry;
import ca.bookstore3005.project.repositories.ReportRepository;

@Service
public class ReportService {

  ReportRepository reportRepository;
  Logger logger;

  ReportService(ReportRepository reportRepository) {
    this.reportRepository = reportRepository;
    this.logger = LoggerFactory.getLogger(BookService.class);
  }
  
  public List<ReportEntry> getAuthorsVsSalesReport(String  publisherName) {
    return reportRepository.findAuthorsVsSalesReport(publisherName);
  }

  public List<ReportEntry> getGenresVsSalesReport(String  publisherName) {
    return reportRepository.findGenresVsSalesReport(publisherName);
  }
  
}
