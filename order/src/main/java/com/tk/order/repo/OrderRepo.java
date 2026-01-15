package com.tk.order.repo;

import com.tk.order.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepo extends JpaRepository <Order, Long> {
    List<Order> findByUserName(String userName);

    Order findByProductName(String productName);
}
