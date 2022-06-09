package com.qu.gradproject.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.qu.gradproject.entity.UserEntity;
import com.qu.gradproject.repository.UserRepository;
 
public class CustomUserDetailsService implements UserDetailsService {
 
    @Autowired
    private UserRepository userRepository;
     
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByEmail(username);
        if (userEntity == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return new CustomUserDetails(userEntity);
    }
 
}