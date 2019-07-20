package com.example.sweater.service;

import com.example.sweater.bean.CreateTodoBean;
import com.example.sweater.model.Todo;

import java.util.List;
import java.util.Optional;


public interface TodoService {

    Todo save(Todo todo);

    /**
     * find all todo list
     * @return
     */
    List<Todo> findAll();

    Optional<Todo> findById(Long id);

    Todo createTodo(CreateTodoBean createTodoBean);

    Todo updateTodo(CreateTodoBean createTodoBean);

    Todo deleteById(Long id);
}
