package ru.innotech.education.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.innotech.education.model.User;

@Repository
public interface UsersRepository extends CrudRepository<User, Long> {
}
