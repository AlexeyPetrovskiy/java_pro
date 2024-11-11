package ru.innotech.education.repository;

import org.springframework.data.repository.CrudRepository;
import ru.innotech.education.dto.UserLimit;

public interface UserLimitRepository extends CrudRepository<UserLimit, Long> {
}
