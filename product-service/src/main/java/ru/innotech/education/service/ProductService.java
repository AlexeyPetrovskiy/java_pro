package ru.innotech.education.service;

import org.springframework.stereotype.Service;
import ru.innotech.education.model.Product;
import ru.innotech.education.model.User;
import ru.innotech.education.repository.ProductRepository;
import ru.innotech.education.repository.UsersRepository;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    private final UsersRepository usersRepository;
    private final ProductRepository productRepository;

    public ProductService(UsersRepository repository, ProductRepository productRepository) {
        this.usersRepository = repository;
        this.productRepository = productRepository;
    }

    public List<Product> getAllUserProducts(long id) {
        List<Product> list = new ArrayList<>();
        User user = usersRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Пользователь не найден"));
        user.getUsersProductsByIdUser().stream().forEach(e -> list.add(e.getProductByIdProduct()));
        return list;
    }

    public Product getProductById(long id){
        return productRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Продукт не найден"));
    }

}
