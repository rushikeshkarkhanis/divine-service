package com.tk.order.resource;

import com.tk.order.entity.Order;
import com.tk.order.model.CancelOrder;
import com.tk.order.service.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/create-order")
    public Order order(@RequestBody Order order) {
        return orderService.createOrder(order);
    }

    @GetMapping("/get-orders/{userName}")
    public List<Order> getOrders(@PathVariable String userName) {
        return orderService.getAllOrders(userName);
    }

    @PutMapping("/cancel-order")
    public Order cancelOrder(@RequestBody CancelOrder order) {
        return orderService.cancelOrder(order);
    }

}
