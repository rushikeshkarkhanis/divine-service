package com.tk.product.repo;

import com.tk.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepo extends JpaRepository <Product, Long> {

    Product findByName(String name);
}
