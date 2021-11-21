package ca.bookstore3005.project.services;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ca.bookstore3005.project.models.Book;
import ca.bookstore3005.project.repositories.BookRepository;

@Service
public class BookService {

  BookRepository bookRepository;
  Logger logger;

  BookService(BookRepository bookRepository) {
    this.bookRepository = bookRepository;
    this.logger = LoggerFactory.getLogger(BookService.class);
  }
  
  /**
   * Retrieves all Books from the database via the repository class
   * Formats the data into Book objects
   * @return the combined list of Book objects
   */

  public List<Book> getAllBooks() {
    return bookRepository.findAllBooks();
  }

  /**
   * 
   * @param isbns
   * @return
   */
  public Book getBookByISBN(String isbn) {
    return bookRepository.findByISBN(isbn);
  }

  /**
   * 
   * @return combined list of Book objects based on given ISBN's
   */
  public List<Book> getBooksByISBN(List<String> isbns) {
    List<Book> books = new ArrayList<>();

    for (String isbn : isbns) {
      Book newBook = bookRepository.findByISBN(isbn);
      
      // If book was found, add it to list of books to return
      if (newBook != null) {
        books.add(newBook);
      }
      
    }

    return books;
  }

  /**
   * Function to tally up the prices of books in cart to get a total cost...
   * 
   * @param books List of books objects to that are in the cart
   * @return Sum of all prices of books in cart (i.e., total)
   */
  public float calculateTotalCost(List<Book> books) {
    float total = 0;
    for (Book book : books) {
      total += book.getPrice();
    }

    return Math.round(total*100)/100;
  }
}
