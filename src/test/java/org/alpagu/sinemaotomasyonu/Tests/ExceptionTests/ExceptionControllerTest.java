package org.alpagu.sinemaotomasyonu.Tests.ExceptionTests;

import org.alpagu.sinemaotomasyonu.Api.Controllers.ExceptionController;
import org.alpagu.sinemaotomasyonu.Core.utilities.Jwt.JwtUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.is;

@WebMvcTest(controllers = ExceptionController.class)
public class ExceptionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserDetailsService userDetailsService; // Mocking the UserDetailsService

    @MockBean
    private JwtUtil jwtUtil; // Mocking JwtUtil

    @Test
    void whenResourceNotFoundException_thenReturns404() throws Exception {
        mockMvc.perform(get("/api/exceptions/resource-not-found"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message", is("Resource not found")));
    }

    @Test
    void whenInvalidInputException_thenReturns400() throws Exception {
        mockMvc.perform(get("/api/exceptions/invalid-input"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", is("Invalid input provided")));
    }

    @Test
    void whenAccessDeniedException_thenReturns403() throws Exception {
        mockMvc.perform(get("/api/exceptions/access-denied"))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message", is("Access denied")));
    }

    @Test
    void whenIllegalArgumentException_thenReturns400() throws Exception {
        mockMvc.perform(get("/api/exceptions/illegal-argument"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", is("Illegal argument provided")));
    }

    @Test
    void whenUnhandledException_thenReturns500() throws Exception {
        mockMvc.perform(get("/api/exceptions/unhandled-exception"))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.message", is("Unhandled exception occurred")));
    }
}
