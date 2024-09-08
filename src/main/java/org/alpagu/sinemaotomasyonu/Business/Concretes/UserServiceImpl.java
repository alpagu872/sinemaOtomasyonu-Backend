package org.alpagu.sinemaotomasyonu.Business.Concretes;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.alpagu.sinemaotomasyonu.Business.Abstracts.UserService;
import org.alpagu.sinemaotomasyonu.DataAccess.Abstracts.UserRepository;
import org.alpagu.sinemaotomasyonu.Entities.Concretes.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }




    public String generateNextUserId() {
        Long count = (Long) entityManager.createQuery("SELECT COUNT(u) FROM User u").getSingleResult();
        return String.valueOf(100 + count);
    }


    @Override
    public User saveUser(User user) {

        if (user.getWebUserId() == null || user.getWebUserId().isEmpty()) {
            user.setWebUserId(generateNextUserId());
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userRepository.save(user);
    }

    @Override
    public User updateUser(String userId, User user) {
        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setEmailId(user.getEmailId());
        existingUser.setAge(user.getAge());
        existingUser.setPhoneNumber(user.getPhoneNumber());
        existingUser.setUsername(user.getUsername());
        existingUser.setRole(user.getRole());
        // If password is being updated, encode it
        if (user.getPassword() != null && !user.getPassword().isEmpty()) {
            existingUser.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        return userRepository.save(existingUser);
    }


    @Override
    public User getUserById(String userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void deleteUser(String userId) {
        userRepository.deleteById(userId);
    }
}
