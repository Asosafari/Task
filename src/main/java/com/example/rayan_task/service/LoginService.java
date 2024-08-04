package com.example.rayan_task.service;


import com.example.rayan_task.model.UserDTO;
import org.springframework.data.domain.Page;

import java.util.Optional;

/**
 * Author: ASOU SAFARI
 * Date:8/4/24
 * Time:1:34 PM
 */

public interface LoginService {
    Optional<String> login(String username, String password);
    boolean validTokenString(String token);
    UserDTO register(UserDTO userDTO);
    Page<UserDTO> listUsers(Integer pageNumber, Integer pageSize);
}
