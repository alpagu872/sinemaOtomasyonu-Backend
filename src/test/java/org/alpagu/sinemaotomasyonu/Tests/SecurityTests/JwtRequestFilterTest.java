package org.alpagu.sinemaotomasyonu.Tests.SecurityTests;

import jakarta.servlet.FilterChain;
import org.alpagu.sinemaotomasyonu.Core.utilities.Jwt.JwtUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;


import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class JwtRequestFilterTest {

    @InjectMocks
    private org.alpagu.sinemaotomasyonu.security.JwtRequestFilter jwtRequestFilter;

    @Mock
    private JwtUtil jwtUtil;

    @Mock
    private UserDetailsService userDetailsService;

    @Mock
    private FilterChain filterChain;

    private MockHttpServletRequest request;
    private MockHttpServletResponse response;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
    }

    @Test
    void testDoFilterInternal_WithValidToken() throws Exception {
        request.addHeader("Authorization", "Bearer valid-jwt-token");

        UserDetails userDetails = org.springframework.security.core.userdetails.User
                .withUsername("user1")
                .password("password")
                .authorities("ROLE_USER")
                .build();

        when(jwtUtil.extractUsername("valid-jwt-token")).thenReturn("user1");
        when(userDetailsService.loadUserByUsername("user1")).thenReturn(userDetails);
        when(jwtUtil.validateToken("valid-jwt-token", userDetails)).thenReturn(true);

        jwtRequestFilter.doFilterInternal(request, response, filterChain);

        assertNotNull(SecurityContextHolder.getContext().getAuthentication());
        assertEquals("user1", SecurityContextHolder.getContext().getAuthentication().getName());
        verify(filterChain, times(1)).doFilter(request, response);
    }

    @Test
    void testDoFilterInternal_WithInvalidToken() throws Exception {
        request.addHeader("Authorization", "Bearer invalid-jwt-token");

        when(jwtUtil.extractUsername("invalid-jwt-token")).thenReturn("user1");
        when(jwtUtil.validateToken("invalid-jwt-token", null)).thenReturn(false);

        jwtRequestFilter.doFilterInternal(request, response, filterChain);

        assertNull(SecurityContextHolder.getContext().getAuthentication());
        verify(filterChain, times(1)).doFilter(request, response);
    }

    @Test
    void testDoFilterInternal_WithoutAuthorizationHeader() throws Exception {
        jwtRequestFilter.doFilterInternal(request, response, filterChain);

        assertNull(SecurityContextHolder.getContext().getAuthentication());
        verify(filterChain, times(1)).doFilter(request, response);
    }
}
