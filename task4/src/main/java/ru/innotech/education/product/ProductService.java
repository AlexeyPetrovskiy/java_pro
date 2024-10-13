package ru.innotech.education.product;

import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class ProductService {

    private final ProductDao productDao;


    public ProductService(ProductDao productDao) {
        this.productDao = productDao;
    }

    public List<Product> getAllUserProducts(long id) throws SQLException {
        return productDao.getAllUserProducts(id);
    }

    public Product getProductById(long id) throws SQLException {
        return productDao.getProductById(id);
    }

}
