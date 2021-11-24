package ca.bookstore3005.project.repositories;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Modifying;
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
  
  @Modifying
  @Query("UPDATE book SET stock = stock - 1 WHERE isbn = :isbn")
  void decreaseBookStock(@Param("isbn") String isbn);

  @Modifying
  @Query("DELETE FROM book WHERE isbn = :isbn;")
  void deleteByISBN(@Param("isbn") String isbn);


  @Modifying
  @Query("INSERT INTO Book values(:isbn, :title, :publisher_name, :stock, :author, :year, :price, :num_pages, :percent_to_publisher, :genre)")
  void addBook(@Param("isbn") String isbn,
                   @Param("title") String title,
                   @Param("publisher_name") String publisher_name,
                   @Param("stock") int stock,
                   @Param("author") String author,
                   @Param("year") int year,
                   @Param("price") float price,
                   @Param("num_pages") int num_pages,
                   @Param("percent_to_publisher") float percent_to_publisher,
                   @Param("genre") String genre);
}
