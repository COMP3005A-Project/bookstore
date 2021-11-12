package ca.bookstore3005.project.services;

import java.util.List;

import org.springframework.stereotype.Service;
import ca.bookstore3005.project.models.Book;
import ca.bookstore3005.project.repositories.BookRepository;

@Service
public class BookService {

  BookRepository bookRepository;

  BookService(BookRepository bookRepository) {
    this.bookRepository = bookRepository;
  }
  
  /**
   * Retrieves all Books from the database via the repository class
   * Formats the data into Book objects
   * @return the combined list of Book objects
   */

  public List<Book> getAllBooks() {
    return bookRepository.findAllBooks();
  }
}
