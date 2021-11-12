package ca.bookstore3005.project.repositories;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ca.bookstore3005.project.models.Book;

@Repository
public interface BookRepository extends CrudRepository<Book, String> {

  @Query("SELECT * FROM book WHERE isbn = :isbn")
  Book findByISBN(String iSBN);

  @Query("SELECT * FROM book")
  List<Book> findAllBooks();
}
