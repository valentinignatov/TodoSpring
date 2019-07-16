package com.example.sweater.service;

import com.example.sweater.model.Todo;

import java.util.List;


public interface TodoService {

    /**
     * find all todo list
     * @return
     */
    List<Todo> findAll();
}
