package ru.innotech.education.repository;

import org.springframework.data.repository.CrudRepository;
import ru.innotech.education.model.Product;

public interface ProductRepository extends CrudRepository<Product, Long> {
}
