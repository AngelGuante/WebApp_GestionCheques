package com.example.demo.security;

import com.example.demo.entities.SecurityUser;
import com.example.demo.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    IUserRepository repo;

    SecurityUser user;

    CustomUserDetails userDetails;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        this.user = this.repo.findByUsername(username);

        if(user != null){
            userDetails = new CustomUserDetails();
            userDetails.setUser(user);

        } else{
            new UsernameNotFoundException("Usuario: "+username+" No Registrado!");
        }

        return userDetails;
    }
}
