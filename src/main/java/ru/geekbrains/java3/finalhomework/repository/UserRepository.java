package ru.geekbrains.java3.finalhomework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.geekbrains.java3.finalhomework.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
