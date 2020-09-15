package com.example.sweater.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
    @Autowired
    UserDetailsRepository userDetailsRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<MyUserDetails2> user = userDetailsRepository.findByUsername(s);

        user.orElseThrow(() -> new UsernameNotFoundException("Not found: " + s));

        System.out.println(user);
        System.out.println(s);

        return user.map(MyUserDetails::new).get();
    }
}
