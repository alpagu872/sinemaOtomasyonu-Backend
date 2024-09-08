package org.alpagu.sinemaotomasyonu.Business.Abstracts;


import org.alpagu.sinemaotomasyonu.Entities.Concretes.User;

import java.util.List;

public interface UserService {
    User saveUser(User user);
    User updateUser(String userId, User user);
    User getUserById(String userId);
    List<User> getAllUsers();
    void deleteUser(String userId);


}

