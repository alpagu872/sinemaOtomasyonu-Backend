package org.alpagu.sinemaotomasyonu.Tests.ControllerTests;

import org.alpagu.sinemaotomasyonu.Business.Abstracts.UserService;
import org.alpagu.sinemaotomasyonu.Api.Controllers.UserController;
import org.alpagu.sinemaotomasyonu.Entities.Concretes.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateUser() throws Exception {
        User user = new User();
        user.setWebUserId("U123");
        user.setFirstName("John");

        when(userService.saveUser(any(User.class))).thenReturn(user);

        mockMvc.perform(post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"webUserId\":\"U123\",\"firstName\":\"John\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.webUserId").value("U123"))
                .andExpect(jsonPath("$.firstName").value("John"));
    }

    @Test
    void testGetUserById() throws Exception {
        User user = new User();
        user.setWebUserId("U123");
        user.setFirstName("John");

        when(userService.getUserById("U123")).thenReturn(user);

        mockMvc.perform(get("/api/users/U123")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.webUserId").value("U123"))
                .andExpect(jsonPath("$.firstName").value("John"));
    }

    @Test
    void testGetAllUsers() throws Exception {
        User user1 = new User();
        user1.setWebUserId("U123");
        User user2 = new User();
        user2.setWebUserId("U124");

        when(userService.getAllUsers()).thenReturn(Arrays.asList(user1, user2));

        mockMvc.perform(get("/api/users")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].webUserId").value("U123"))
                .andExpect(jsonPath("$[1].webUserId").value("U124"));
    }

    @Test
    void testDeleteUser() throws Exception {
        doNothing().when(userService).deleteUser("U123");

        mockMvc.perform(delete("/api/users/U123")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}
