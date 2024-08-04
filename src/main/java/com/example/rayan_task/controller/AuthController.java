package com.example.rayan_task.controller;


import com.example.rayan_task.model.UserDTO;
import com.example.rayan_task.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * Author: ASOU SAFARI
 * Date:8/4/24
 * Time:1:43 PM
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final LoginService loginService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestParam(required = false) String username, @RequestParam(required = false) String password){
        Optional<String> token = loginService.login(username,password);
        if (token.isPresent()) {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization", token.get());
            return new ResponseEntity<>(headers, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity("Unauthorized",HttpStatus.UNAUTHORIZED);
    }


    @PostMapping("/register")
    public ResponseEntity registerUser(@Validated @RequestBody UserDTO userDTO,
                                         @RequestHeader(name = "Token") String token){

        if (loginService.validTokenString(token)) {
            UserDTO saveUserDto = loginService.register(userDTO);
            return new ResponseEntity("Create", HttpStatus.CREATED);
        }
        return new ResponseEntity("Unauthorized",HttpStatus.UNAUTHORIZED);
    }

    @GetMapping("/users")
    public Page<UserDTO> getUsers(@RequestParam(required = false)Integer pageNumber,
                                  @RequestParam(required = false) Integer pageSize,
                                  @RequestHeader(name = "Token") String token){

        if (loginService.validTokenString(token)){
            return loginService.listUsers(pageNumber,pageSize);
        }else {
            throw new RuntimeException("Unauthorized");
        }
    }

}
