package ca.bookstore3005.project.models;

import org.springframework.data.annotation.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * View data object used to contruct reports table.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReportEntry {
  
  @Id
  private String category;
  private double total;
}