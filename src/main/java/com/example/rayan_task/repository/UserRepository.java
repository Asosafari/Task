package com.example.rayan_task.repository;

import com.example.rayan_task.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

/**
 * Author: ASOU SAFARI
 * Date:8/3/24
 * Time:5:07 PM
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Optional<User> findByToken(String token);
}
