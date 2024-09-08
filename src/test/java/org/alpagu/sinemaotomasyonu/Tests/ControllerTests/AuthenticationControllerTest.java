package org.alpagu.sinemaotomasyonu.Tests.ControllerTests;
import org.alpagu.sinemaotomasyonu.Api.Controllers.AuthenticationController;
import org.alpagu.sinemaotomasyonu.Business.Concretes.UserDetailsServiceImpl;
import org.alpagu.sinemaotomasyonu.Core.utilities.Jwt.JwtUtil;
import org.alpagu.sinemaotomasyonu.DataAccess.Abstracts.UserRepository;
import org.alpagu.sinemaotomasyonu.Entities.Dtos.LoginRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.is;

@WebMvcTest(AuthenticationController.class)
class AuthenticationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthenticationManager authenticationManager;

    @MockBean
    private JwtUtil jwtUtil;

    @MockBean
    private UserDetailsServiceImpl userDetailsService;

    @MockBean
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateAuthenticationToken() throws Exception {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setPhoneNumber("1234567890");
        loginRequest.setPassword("password");

        UserDetails userDetails = org.springframework.security.core.userdetails.User
                .withUsername("1234567890")
                .password("password")
                .authorities("ROLE_USER")
                .build();

        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenReturn(new UsernamePasswordAuthenticationToken(loginRequest.getPhoneNumber(), loginRequest.getPassword(), userDetails.getAuthorities()));
        when(userDetailsService.loadUserByUsername("1234567890")).thenReturn(userDetails);
//        when(jwtUtil.generateToken(userDetails)).thenReturn("fake-jwt-token");

        mockMvc.perform(post("/api/authenticate")
                        .contentType("application/json")
                        .content("{\"phoneNumber\":\"1234567890\",\"password\":\"password\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", is("fake-jwt-token")));
    }
}
