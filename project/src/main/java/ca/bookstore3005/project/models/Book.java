package ca.bookstore3005.project.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * View data object used to contruct book table.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {
  
  @Id
  private String ISBN;
  private String title;
  private String author;
  private String year;
  private String price;
  private String stock;
}