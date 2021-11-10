package ca.bookstore3005.project.models;

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

  private String ISBN;
  private String title;
  private String author;
  private String release; //TODO: switch to date
  private String price;
  private String stock;
}