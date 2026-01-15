package com.tk.order.service;

import com.tk.order.entity.Order;
import com.tk.order.entity.OrderStatus;
import com.tk.order.model.CancelOrder;
import com.tk.order.repo.OrderRepo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrderService {

    private final OrderRepo orderRepo;
    private final RestTemplate restTemplate;

    public OrderService(OrderRepo orderRepo, RestTemplate restTemplate) {
        this.orderRepo = orderRepo;
        this.restTemplate = restTemplate;
    }
    public List<Order> getAllOrders(String userName) {
        return orderRepo.findByUserName(userName);
    }

    public Order createOrder(Order order) {
        String userUrl = "http://localhost:9091/api/v1/get-user-by-name/{userName}";
        String orderUrl = "http://localhost:9092/api/v1/order-product/{name}/{qty}";

        Map<String, Object> params = new HashMap<>();
        params.put("name", order.getProductName());
        params.put("qty", order.getProductQuantity());

        try {
            ResponseEntity<String> response = restTemplate.getForEntity(
                    userUrl,
                    String.class,
                    order.getUserName()
            );
            if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
                restTemplate.put(orderUrl, null, params);
                System.out.println("Product order updated successfully!");
            } else {
                return null;
            }
        } catch (Exception e) {
            System.err.println("Error calling Product Service: " + e.getMessage());
        }
        System.out.println("Order creates successfully in order table!");
        return orderRepo.save(order);
    }

    public Order cancelOrder(CancelOrder order) {
        String orderUrl = "http://localhost:9092/api/v1/cancel-product/{name}/{qty}";

        Map<String, Object> params = new HashMap<>();
        params.put("name", order.getProductName());
        params.put("qty", order.getProductQuantity());
        Order o = new Order();
        o.setProductName(order.getProductName());
        o.setProductQuantity(order.getProductQuantity());
        o.setUserName(order.getUserName());
        o.setOrderStatus(OrderStatus.CANCELLED);
        try {
            restTemplate.put(orderUrl, null, params);

            System.out.println("Product order updated successfully!");
        } catch (Exception ex) {
            System.err.println("Error calling Product Service: " + ex.getMessage());
        }
        System.out.println("Order Cancelled successfully in order table!");
        return orderRepo.save(o);
    }
}
