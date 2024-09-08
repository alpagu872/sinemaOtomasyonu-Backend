package org.alpagu.sinemaotomasyonu.Tests.ExceptionTests;

import org.alpagu.sinemaotomasyonu.Business.Concretes.UserDetailsServiceImpl;
import org.alpagu.sinemaotomasyonu.Core.utilities.Jwt.JwtUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test") // Activate the test profile

public class GlobalExceptionHandlerLogTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserDetailsServiceImpl userDetailsService;

    @MockBean
    private JwtUtil jwtUtil;

    @BeforeEach
    public void setUp() {
        // Mock the behavior of JwtUtil to return a known username
        when(jwtUtil.extractUsername(anyString())).thenReturn("5414257156");

        // Mock the behavior of UserDetailsServiceImpl to return a known UserDetails
        when(userDetailsService.loadUserByUsername("5414257156"))
                .thenReturn(new User("5414257156", "password", new ArrayList<>()));
    }

    @Test
    @WithMockUser(username = "testUser")
    public void whenResourceNotFoundException_thenLogged() throws Exception {
        mockMvc.perform(get("/resources/999")
                        .header("Authorization", "Bearer mockedToken"))
                .andExpect(status().isNotFound());
    }

}
