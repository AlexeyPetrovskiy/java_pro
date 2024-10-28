package ru.innotech.education.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.innotech.education.model.Product;
import ru.innotech.education.service.ProductService;

import java.util.List;

@RestController
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    //запросить все продукты по userId
    @GetMapping(value = "/userproducts/{id}")
    public List<Product> getAllUserProducts(@PathVariable long id) {
        return productService.getAllUserProducts(id);
    }

    //запросить продукт по productId
    @GetMapping(value = "/product/{id}")
    public Product getProducts(@PathVariable long id) {
        return productService.getProductById(id);
    }
}
