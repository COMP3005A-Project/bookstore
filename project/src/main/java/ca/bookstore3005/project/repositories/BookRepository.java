package ca.bookstore3005.project.repositories;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ca.bookstore3005.project.models.Book;

@Repository
public interface BookRepository extends CrudRepository<Book, String> {

  @Query("SELECT * FROM book WHERE isbn = :isbn")
  Book findByISBN(@Param("isbn") String isbn);

  @Query("SELECT * FROM book")
  List<Book> findAllBooks();
}
