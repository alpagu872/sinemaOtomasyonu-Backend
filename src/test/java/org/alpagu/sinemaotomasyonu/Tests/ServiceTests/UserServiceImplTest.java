package org.alpagu.sinemaotomasyonu.Tests.ServiceTests;

import org.alpagu.sinemaotomasyonu.Business.Concretes.UserServiceImpl;
import org.alpagu.sinemaotomasyonu.DataAccess.Abstracts.UserRepository;
import org.alpagu.sinemaotomasyonu.Entities.Concretes.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveUser() {
        User user = new User();
        user.setWebUserId("U123");
        user.setFirstName("John");
        user.setLastName("Doe");

        when(userRepository.save(any(User.class))).thenReturn(user);

        User savedUser = userService.saveUser(user);

        assertNotNull(savedUser);
        assertEquals("U123", savedUser.getWebUserId());
        assertEquals("John", savedUser.getFirstName());
    }

    @Test
    void testGetUserById() {
        User user = new User();
        user.setWebUserId("U123");

        when(userRepository.findById("U123")).thenReturn(Optional.of(user));

        User foundUser = userService.getUserById("U123");

        assertNotNull(foundUser);
        assertEquals("U123", foundUser.getWebUserId());
    }

    @Test
    void testDeleteUser() {
        doNothing().when(userRepository).deleteById("U123");

        userService.deleteUser("U123");

        verify(userRepository, times(1)).deleteById("U123");
    }
}
