package ca.bookstore3005.project.models;

import org.springframework.data.annotation.Id;
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
  private String publisher_name;
  private String year;
  private float  price;
  private float  percent_to_publisher;
  private int    stock;
}