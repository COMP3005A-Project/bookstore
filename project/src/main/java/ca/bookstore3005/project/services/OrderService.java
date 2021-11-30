package ca.bookstore3005.project.services;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.stereotype.Service;

import ca.bookstore3005.project.models.Book;
import ca.bookstore3005.project.models.Order;
import ca.bookstore3005.project.repositories.OrderRepository;
import ca.bookstore3005.project.repositories.RegionRepository;

@Service
public class OrderService {

    private OrderRepository orderRepository;
    private RegionRepository regionRepository;

    OrderService(OrderRepository orderRepository, RegionRepository regionRepository) {
        this.orderRepository = orderRepository;
        this.regionRepository = regionRepository;
    }

    /**
     * Retrieve all orders from database via repository class
     * 
     * @return
     */
    public List<Order> getAllOrders() {
        return orderRepository.findAllOrders();
    }

    /**
     * Retrieve all orders from database based on user's email
     * 
     * @param email
     * @return
     */
    public List<Order> getOrdersFromUser(String email) {
        return orderRepository.findOrdersByUser(email);
    }

    /**
     * Retrieve specific order from the database based on order id
     * 
     * @param orderId
     * @return
     */
    public Order getOrderById(int orderId) {
        return orderRepository.findByOrderId(orderId);
    }

    /**
     * Retrieve specific order from the database based on order id
     * 
     * @param shippingId
     * @return
     */
    public Order getOrderByShippingId(String shippingId) {
        return orderRepository.findByShippingId(shippingId);
    }

    /**
     * Find books under a given order number
     * 
     * @param orderId 
     * @return List of books associated with the order_id
     */
    public List<Book> getBooksInOrder(int orderId) {
        return orderRepository.findBooksInOrder(orderId);
    }

    /**
     * Add new order under given user's email
     * 
     * @param email Email address to add order under
     * @return Id of the newly created order
     */
    public long addOrder(String email, Timestamp date, String shippingId) {
        return orderRepository.addOrder(email, date, shippingId);
    }

    /**
     * Add new order under given user's email
     * 
     * @param email Email address to add order under
     * @return Id of the newly created order
     */
    public long addOrder(String email, Timestamp date, String shippingId, String streetNum, String streetName, String postal, String city, String province) {
        // Save postal separately
        regionRepository.addRegion(postal, city, province);
        // Save order
        return orderRepository.addOrder(email, date, shippingId, streetNum, streetName, postal);
    }

    /**
     * Add book under specific order
     * 
     * @param order_id Order ID to tie book to
     * @param isbn ISBN of book to add to order
     * 
     */
    public void addBookToOrder(Long order_id, String isbn) {
        orderRepository.addBookToOrder(order_id, isbn);
    }

    /**
     * Add book under specific order
     * 
     * @param order_id Order ID to tie book to
     * @param books_in_order List of ISBNs of books in the order
     * 
     */
    public void addBooksToOrder(Long order_id, List<String> books_in_order) {
        for (String isbn : books_in_order) {
            orderRepository.addBookToOrder(order_id, isbn);
        }
    }
}
