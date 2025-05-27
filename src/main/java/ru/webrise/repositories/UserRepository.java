package ru.webrise.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.webrise.entities.Subscription;
import ru.webrise.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
