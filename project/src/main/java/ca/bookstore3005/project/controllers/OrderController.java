package ca.bookstore3005.project.controllers;

import java.util.List;
import java.util.UUID;
import java.sql.Timestamp;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import ca.bookstore3005.project.models.Book;
import ca.bookstore3005.project.models.Order;
import ca.bookstore3005.project.services.OrderService;

@Controller
public class OrderController {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    private OrderService orderService;

    OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/order")
    public String order(@RequestParam String orderId, Model model) {

        List<Book> booksInOrder = orderService.getBooksInOrder(Integer.parseInt(orderId));

        model.addAttribute("order", orderService.getOrderById(Integer.parseInt(orderId)));
        model.addAttribute("booksInOrder", booksInOrder);

        return "order";
    }

    @PostMapping("/placeOrder")
    public RedirectView orderView(HttpSession session) {
        logger.info("NEW NEW ORDER INCOMING....");

        // Get current datetime and format to a proper timestamp
        long now = System.currentTimeMillis(); 
        Timestamp timestamp = new Timestamp(now);
        // Create unique shipping Id
        String shippingId = UUID.randomUUID().toString().replace("-", "").substring(0, 11);

        // Create new order, grab new order id for adding books
        long orderId = orderService.addOrder((String) session.getAttribute("user_email"), timestamp, shippingId);

        // Tie books in cart to new order
        @SuppressWarnings("unchecked")
        List<String> cart = (List<String>) session.getAttribute("cart");

        for (String isbn : cart) {
            orderService.addBookToOrder(orderId, isbn);
        }

        return new RedirectView(String.format("/order?orderId=%d", orderId));
    }

    @GetMapping("/orders")
    public String orders(Model model, HttpSession session) {
        String customerEmail = (String) session.getAttribute("user_email");
        
        // Get all orders under the current users email
        List<Order> orders = orderService.getOrdersFromUser(customerEmail);

        model.addAttribute("allOrders", orders);
        model.addAttribute("module", "orders");

        return "orders";
    }

}
