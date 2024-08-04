package com.example.rayan_task.bootstrap;
import com.example.rayan_task.config.Token;
import com.example.rayan_task.entity.User;
import com.example.rayan_task.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Author: ASOU SAFARI
 * Date:8/4/24
 * Time:1:13 PM
 */
@Component
@RequiredArgsConstructor
public class DataBootstrap implements CommandLineRunner {
   private final UserRepository userRepository;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
      load();
    }

    private void load() {
        userRepository.save(User.builder()
                .id(UUID.randomUUID())
                .username("www.admin@example.com")
                .password("123456")
                .version(1)
                .token(Token.generatetokenString())
                .tokenCreatedTime(LocalDateTime.now())
                .build());

    }
}