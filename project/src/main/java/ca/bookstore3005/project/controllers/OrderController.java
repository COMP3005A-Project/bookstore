package ca.bookstore3005.project.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    public String book(@RequestParam String orderId, Model model) {

        model.addAttribute("order", orderService.getOrderById(orderId));

        return "order";
    }

    @GetMapping("/orders")
    public String books(Model model, HttpSession session) {
        @SuppressWarnings("unchecked")
        String customerEmail = (String) session.getAttribute("user_email");
        
        // Get all orders under the current users email
        List<Order> orders = orderService.getOrdersFromUser(customerEmail);

        model.addAttribute("allOrders", orders);
        model.addAttribute("module", "orders");

        return "orders";
    }

}
