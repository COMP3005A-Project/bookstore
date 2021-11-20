package ca.bookstore3005.project.services;

import java.util.ArrayList;
import java.util.List;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ca.bookstore3005.project.models.Order;
import ca.bookstore3005.project.repositories.OrderRepository;

@Service
public class OrderService {

    OrderRepository orderRepository;
    Logger logger;

    OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
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
    public Order getOrderById(String orderId) {
        return orderRepository.findByOrderId(Integer.parseInt(orderId));
    }

    /**
     * Retrieve specific order from the database based on order id
     * 
     * @param shippingId
     * @return
     */
    public Order getOrderByShippingId(String shippingId) {
        return orderRepository.findByShippingId(Integer.parseInt(shippingId));
    }

    /**
     * Add new order under given user's email
     * 
     * @param email Email address to add order under
     * @return Id of the newly created order
     */
    public long addOrder(String email) {
        return orderRepository.addOrder(email);
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
}
