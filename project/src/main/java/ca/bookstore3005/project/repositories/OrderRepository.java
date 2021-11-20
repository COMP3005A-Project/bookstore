package ca.bookstore3005.project.repositories;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ca.bookstore3005.project.models.Order;

@Repository
public interface OrderRepository extends CrudRepository<Order, String> {

    @Query("SELECT * FROM book_order WHERE order_id = :order_id")
    Order findByOrderId(@Param("order_id") int order_id);

    @Query("SELECT * FROM book_order WHERE shipping_id = :shipping_id")
    Order findByShippingId(@Param("shipping_id") int shipping_id);

    @Query("SELECT * FROM book_order")
    List<Order> findAllOrders();

    @Query("SELECT * FROM book_order WHERE email = :email")
    List<Order> findOrdersByUser(@Param("email") String email);

    @Query("INSERT INTO book_order(email) values(:email) returning order_id;")
    long addOrder(@Param("email") String name);

    @Query("INSERT INTO books_in_order(order_id, isbn, amount) values(:order_id, :isbn, 1) returning order_id;")
    void addBookToOrder(@Param("order_id") long order_id, @Param("isbn") String isbn);
}
