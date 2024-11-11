package ru.innotech.education.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.innotech.education.dto.UserLimit;

public interface UserLimitRepository extends JpaRepository<UserLimit, Long> {
}
