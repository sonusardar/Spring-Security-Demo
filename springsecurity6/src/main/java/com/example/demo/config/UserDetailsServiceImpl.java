package com.example.demo.config;

import com.example.demo.entity.UserDtls;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserDtls user = this.userRepository.findByEmail(username);
        System.out.println("===========UserDetailsServiceImpl============");
        System.out.println(user.toString());

        if (user !=null){
            return new CustomUserDetails(user);
        }

        throw new UsernameNotFoundException("user not available");
    }
}
