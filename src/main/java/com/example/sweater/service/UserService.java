package com.example.sweater.service;

import com.example.sweater.bean.CreateTodoBean;
import com.example.sweater.bean.UserWithNumberOfTodos;
import com.example.sweater.model.Todo;
import com.example.sweater.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    /**
     * Find all user list
     * @return
     */
    List<User> findAll();

    Optional<User> findById(Long id);

    Todo createUserTodo(Long userId, CreateTodoBean createTodoBean);

    Todo updateUserTodo(Long userId, Long todoId, CreateTodoBean createTodoBean);

    Todo deleteById(Long userId, Long todoId);

    List<UserWithNumberOfTodos> numberOfTodos();
}
