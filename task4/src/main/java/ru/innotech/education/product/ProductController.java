package ru.innotech.education.product;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.List;

@RestController
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    //запросить все продукты по userId
    @GetMapping(value = "/userproducts/{id}")
    public List<Product> getAllUserProducts(@PathVariable long id) throws SQLException {
        return productService.getAllUserProducts(id);
    }

    //запросить продукт по productId
    @GetMapping(value = "/product/{id}")
    public Product getProducts(@PathVariable long id) throws SQLException {
        return productService.getProductById(id);
    }

}
