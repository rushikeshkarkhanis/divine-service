package com.tk.product.resource;

import com.tk.product.entity.Product;
import com.tk.product.service.ProductService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/add-product")
    public Product createproduct(@RequestBody Product product) {
        return productService.createProduct(product);
    }

    @GetMapping("/get-products")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/get-product/{id}")
    public Product getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    @PutMapping("/update-product/{name}")
    public Product updateProduct(@PathVariable Long id, @RequestBody Product product) {
        return productService.updateProduct(id, product);
    }

    @PutMapping("/order-product/{name}/{quantity}")
    public Product orderProduct(@PathVariable String name, @PathVariable Integer quantity) {
        return productService.orderProduct(name, quantity);
    }

    @PutMapping("/cancel-product/{name}/{quantity}")
    public Product cancelProduct(@PathVariable String name, @PathVariable Integer quantity) {
        return productService.cancelProduct(name, quantity);
    }

    @DeleteMapping("/delete-product/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return "product deleted successfully with id: " + id;
    }
}
