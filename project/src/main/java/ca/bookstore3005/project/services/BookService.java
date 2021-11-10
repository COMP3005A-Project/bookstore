package ca.bookstore3005.project.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import ca.bookstore3005.project.models.Book;

@Service
public class BookService {
  
  /**
   * Retrieves all Books from the database via the repository class
   * Formats the data into Book objects
   * @return the combined list of Book objects
   */

  public List<Book> getAllBooks() {
    
    //TODO: retrieve real data and format into Book data types
    List<Book> books = new ArrayList<Book>();
    books.add(new Book("101", "Name of the Wind", "Patrick Rothfuss", "2011/04/25", "$40", "20"));
    books.add(new Book("102", "Doors of Stone", "Patrick Rothfuss", "2021/02/15", "$60", "1"));
    books.add(new Book("103", "The Way of Kings", "Brandon Sanderson", "2016/01/05", "$45", "30"));

    return books;
  }
}
