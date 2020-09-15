package com.example.sweater.security;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserDetailsRepository extends JpaRepository<MyUserDetails2, Long> {
    Optional<MyUserDetails2> findByUsernameIgnoreCase(String username);

    Optional<MyUserDetails2> findByUsername(String username);
}
