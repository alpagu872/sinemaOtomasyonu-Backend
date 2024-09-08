package org.alpagu.sinemaotomasyonu.DataAccess.Abstracts;

import org.alpagu.sinemaotomasyonu.Entities.Concretes.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    User findByEmailId(String username);
    User findByPhoneNumber(String phoneNumber);
    boolean existsByPhoneNumber(String phoneNumber);
//    Optional<User> findByPhoneNumber(String phoneNumber);

    User getByWebUserId(String webUserId);
    @Query("SELECT MAX(u.webUserId) FROM User u")
    Optional<String> findMaxUserId();




}
