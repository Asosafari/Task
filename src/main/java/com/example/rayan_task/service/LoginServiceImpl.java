package com.example.rayan_task.service;
import com.example.rayan_task.config.Token;
import com.example.rayan_task.entity.User;
import com.example.rayan_task.mapper.UserMapper;
import com.example.rayan_task.model.UserDTO;
import com.example.rayan_task.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Author: ASOU SAFARI
 * Date:8/4/24
 * Time:1:34 PM
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class LoginServiceImpl implements LoginService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public Optional<String> login(String username, String password) {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isEmpty()){
            log.warn("user not Found");
            return Optional.empty();
        }else {
            if (user.get().getPassword().equals(password)){
                user.get().setToken(Token.generatetokenString());
                user.get().setTokenCreatedTime(LocalDateTime.now());
                updateTokenUser(user.get());
            }
            else {
                log.info("password invalid");
                return Optional.empty();
            }
        }

        return Optional.of(user.get().getToken());
    }

    @Override
    public boolean validTokenString(String token){
        Optional<User> user = userRepository.findByToken(token);
        if (user.isPresent()){
            return user.get().getTokenCreatedTime().isAfter(LocalDateTime.now().minusSeconds(1800));
        }
        log.info("Token Expired");
        return false;
    }

    @Override
    public UserDTO register(UserDTO userDTO) {
        User user = User.builder()
                .username(userDTO.getUsername())
                .password(userDTO.getPassword())
                .id(UUID.randomUUID())
                .version(1)
                .build();
        user = userRepository.save(user);
        return UserDTO.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .build();
    }

    private User updateTokenUser(User user){
        AtomicReference<User> userAtomicReference = new AtomicReference<>();
        userRepository.findByUsername(user.getUsername()).ifPresent(foundUser ->{
            foundUser.setId(foundUser.getId());
            foundUser.setUsername(user.getUsername());
            foundUser.setPassword(user.getPassword());
            foundUser.setToken(Token.generatetokenString());
            foundUser.setTokenCreatedTime(LocalDateTime.now());
            userAtomicReference.set(userRepository.save(user));
        });
        return userAtomicReference.get();
    }

    @Override
    public Page<UserDTO> listUsers(Integer pageNumber, Integer pageSize) {
        PageRequest pageRequest = BuildPageRequest.build(pageNumber,pageSize);
        return userRepository.findAll(pageRequest).map(user -> UserDTO.builder()
                .password(user.getPassword())
                .username(user.getUsername())
                .build());

    }


}
