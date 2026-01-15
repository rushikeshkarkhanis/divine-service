package com.tk.order.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userName;
    private String productName;
    private String productQuantity;
    @Enumerated(EnumType.ORDINAL)
    private OrderStatus orderStatus;
}
