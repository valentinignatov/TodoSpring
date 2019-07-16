package com.example.sweater.service;

import com.example.sweater.model.User;

import java.util.List;

public interface UserService {

    /**
     * Find all user list
     * @return
     */
    List<User> findAll();
}
