package org.alpagu.sinemaotomasyonu.Api.Controllers;

import org.alpagu.sinemaotomasyonu.Business.Abstracts.UserService;
import org.alpagu.sinemaotomasyonu.Business.Concretes.UserServiceImpl;
import org.alpagu.sinemaotomasyonu.Entities.Concretes.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    private final UserServiceImpl userServiceImpl;

    @Autowired
    public UserController(UserService userService, UserServiceImpl userServiceImpl) {
        this.userService = userService;
        this.userServiceImpl = userServiceImpl;
    }


    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User savedUser = userService.saveUser(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<User> updateUser(@PathVariable String id, @RequestBody User user) {
        User updatedUser = userService.updateUser(id, user);
        return ResponseEntity.ok(updatedUser);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<User> getUserById(@PathVariable String id) {
        User user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @DeleteMapping("delete/{id}")

    public ResponseEntity<Void> deleteUser(@PathVariable String id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
