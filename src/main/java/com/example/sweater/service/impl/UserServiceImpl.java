package com.example.sweater.service.impl;

import com.example.sweater.bean.CreateTodoBean;
import com.example.sweater.model.Todo;
import com.example.sweater.model.User;
import com.example.sweater.repository.UserRepository;
import com.example.sweater.service.TodoService;
import com.example.sweater.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    private TodoService todoService;

    public UserServiceImpl(UserRepository userRepository,
                           TodoService todoService) {
        this.userRepository = userRepository;
        this.todoService = todoService;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findById(Long id) { return userRepository.findById(id); }

    @Override
    public Long howMuchTodos(String name) {
        return null;
    }

    @Override
    public Todo createUserTodo(Long userId, CreateTodoBean createTodoBean) {

        if (!userRepository.findById(userId).isPresent()) {
            // throw exception
        }

        createTodoBean.setUserId(userId);

        return todoService.createTodo(createTodoBean);
    }
}
