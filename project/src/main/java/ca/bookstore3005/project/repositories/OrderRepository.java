package ca.bookstore3005.project.repositories;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ca.bookstore3005.project.models.Book;
import ca.bookstore3005.project.models.Order;

@Repository
public interface OrderRepository extends CrudRepository<Order, String> {

    @Query("SELECT * FROM book_order WHERE order_id = :order_id")
    Order findByOrderId(@Param("order_id") int order_id);

    @Query("SELECT * FROM book_order WHERE shipping_id = :shipping_id")
    Order findByShippingId(@Param("shipping_id") String shipping_id);

    @Query("SELECT * FROM book_order")
    List<Order> findAllOrders();

    @Query("SELECT * FROM book_order WHERE email = :email")
    List<Order> findOrdersByUser(@Param("email") String email);

    @Query("SELECT isbn, title, author, year, price, stock FROM books_in_order JOIN book using(isbn) WHERE order_id = :order_id;")
    List<Book> findBooksInOrder(@Param("order_id") int order_id);

    @Query("INSERT INTO book_order(email, date, shipping_id) values(:email, :date, :shipping_id) returning order_id")
    long addOrder(@Param("email") String email, @Param("date") Timestamp date, @Param("shipping_id") String shipping_id);

    @Query("INSERT INTO book_order(email, date, shipping_id, address_street_num, address_street_name, address_street_postal, city, province) values(:email, :date, :shipping_id, :street_num, :street_name, :postal, :city, :province) returning order_id")
    long addOrder(@Param("email") String email, @Param("date") Timestamp date, @Param("shipping_id") String shipping_id, @Param("street_num") String street_num, @Param("street_name") String street_name, @Param("postal") String postal, @Param("city") String city, @Param("province") String province);

    @Modifying
    @Query("INSERT INTO books_in_order(order_id, isbn, amount) values(:order_id, :isbn, 1)")
    void addBookToOrder(@Param("order_id") long order_id, @Param("isbn") String isbn);
}
