package com.tk.product.repo;

import com.tk.product.entity.Category;
import com.tk.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepo extends JpaRepository <Category, Long> {
}
