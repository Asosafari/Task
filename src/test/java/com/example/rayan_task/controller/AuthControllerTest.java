package com.example.rayan_task.controller;

import com.example.rayan_task.RayanTaskApplication;
import com.example.rayan_task.model.UserDTO;
import com.example.rayan_task.service.LoginService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = RayanTaskApplication.class)
@WebMvcTest(controllers = AuthController.class)
public class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LoginService loginService;

    @MockBean
    private UserService userService;

    @InjectMocks
    private AuthController authController;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(authController).build();
    }

    @Test
    void testLoginSuccess() throws Exception {
        String token = "sampleToken";
        given(loginService.login(anyString(), anyString())).willReturn(Optional.of(token));

        mockMvc.perform(post("/auth/login")
                        .param("username", "testUser")
                        .param("password", "testPass")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent())
                .andExpect(header().string("Authorization", token));

    }

    @Test
    void testLoginFailure() throws Exception {
        given(loginService.login(anyString(), anyString())).willReturn(Optional.empty());

        mockMvc.perform(post("/auth/login")
                        .param("username", "testUser")
                        .param("password", "wrongPass")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void testRegisterUser() throws Exception {
        UserDTO userDTO = UserDTO.builder().build();
        userDTO.setUsername("testUser");
        userDTO.setPassword("testPass");

        given(loginService.register(userDTO)).willReturn(userDTO);

        mockMvc.perform(post("/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\": \"testUser\", \"password\": \"testPass\"}"))
                .andExpect(status().isNoContent())
                .andExpect(header().string("Authorization", "sampleToken"));
    }
}
