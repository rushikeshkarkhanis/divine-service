package com.tk.order.service;

import com.tk.order.entity.Order;
import com.tk.order.entity.OrderStatus;
import com.tk.order.model.CancelOrder;
import com.tk.order.repo.OrderRepo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrderService {

    private final String userUrl;
    private final String productUrl;

    private final OrderRepo orderRepo;
    private final RestTemplate restTemplate;

    public OrderService(@Value("${api.user.get-by-name}") String userUrl,
                        @Value("${api.product.update-order}") String productUrl,
                        OrderRepo orderRepo, RestTemplate restTemplate) {
        this.userUrl = userUrl;
        this.productUrl = productUrl;
        this.orderRepo = orderRepo;
        this.restTemplate = restTemplate;
    }
    public List<Order> getAllOrders(String userName) {
        return orderRepo.findByUserName(userName);
    }

    public Order createOrder(Order order) {

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
                restTemplate.put(productUrl, null, params);
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
        Map<String, Object> params = new HashMap<>();
        params.put("name", order.getProductName());
        params.put("qty", order.getProductQuantity());
        Order o = new Order();
        o.setProductName(order.getProductName());
        o.setProductQuantity(order.getProductQuantity());
        o.setUserName(order.getUserName());
        o.setOrderStatus(OrderStatus.CANCELLED);
        try {
            restTemplate.put(productUrl, null, params);
            System.out.println("Product order updated successfully!");
        } catch (Exception ex) {
            System.err.println("Error calling Product Service: " + ex.getMessage());
        }
        System.out.println("Order Cancelled successfully in order table!");
        return orderRepo.save(o);
    }
}
