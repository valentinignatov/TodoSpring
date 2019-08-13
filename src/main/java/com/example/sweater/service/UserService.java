package com.example.sweater.service;

import com.example.sweater.bean.CreateTodoBean;
import com.example.sweater.bean.UserBean;
import com.example.sweater.model.Todo;
import com.example.sweater.model.User;

import java.util.List;

public interface UserService {

    /**
     * Find all user list
     *
     * @return
     */
    List<User> findAll();

    /**
     * Find user by id
     *
     * @param id an existing user id
     * @return
     */
    User findById(Long id);

    Todo createUserTodo(Long userId, CreateTodoBean createTodoBean);

    Todo updateUserTodo(Long userId, Long todoId, CreateTodoBean createTodoBean);

    Todo deleteById(Long userId, Long todoId);

    List<UserBean> findAllUserBean();
}
