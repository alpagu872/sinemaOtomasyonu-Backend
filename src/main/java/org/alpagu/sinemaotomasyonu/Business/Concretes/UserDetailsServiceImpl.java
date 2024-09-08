package org.alpagu.sinemaotomasyonu.Business.Concretes;

import org.alpagu.sinemaotomasyonu.DataAccess.Abstracts.UserRepository;
import org.alpagu.sinemaotomasyonu.Entities.Concretes.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String phoneNumber) throws UsernameNotFoundException {
        User webUser = userRepository.findByPhoneNumber(phoneNumber);
        if (webUser == null) {
            throw new UsernameNotFoundException("User not found with phone number: " + phoneNumber);
        }
        return new org.springframework.security.core.userdetails.User(webUser.getPhoneNumber(), webUser.getPassword(), new ArrayList<>());
    }
}
