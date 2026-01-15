package com.tk.product.service;

import com.tk.product.entity.Product;
import com.tk.product.repo.ProductRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepo productRepo;

    public ProductService(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    public Product getProductById(Long id) {
        return productRepo.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
    }

    public Product createProduct(Product Product) {
        return productRepo.save(Product);
    }

    public Product updateProduct(Long id, Product product) {
        Product p = getProductById(id);
        p.setName(product.getName());
        p.setBrand(product.getBrand());
        p.setCategory(product.getCategory());
        p.setQuantity(product.getQuantity());
        return productRepo.save(p);
    }

    public Product orderProduct(String name, Integer quantity) {
        Product product = productRepo.findByName(name);
        int newProductQuantity = product.getQuantity() - quantity;
        product.setQuantity(newProductQuantity);
        return productRepo.save(product);
    }

    public Product cancelProduct(String name, Integer quantity) {
        Product product = productRepo.findByName(name);
        int newProductQuantity = product.getQuantity() + quantity;
        product.setQuantity(newProductQuantity);
        return productRepo.save(product);
    }

    public void deleteProduct(Long id) {
        productRepo.deleteById(id);
    }
}
