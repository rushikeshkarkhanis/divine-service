package com.tk.product.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String brand;
    private Integer quantity;
    @ManyToOne(fetch = FetchType.EAGER) // Add this annotation!
    @JoinColumn(name = "category_id")   // This creates the foreign key column
    private Category category;
}
