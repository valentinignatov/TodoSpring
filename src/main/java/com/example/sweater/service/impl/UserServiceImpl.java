package com.example.sweater.service.impl;

import com.example.sweater.bean.CreateTodoBean;
import com.example.sweater.bean.UserWithNumberOfTodos;
import com.example.sweater.model.Todo;
import com.example.sweater.model.User;
import com.example.sweater.repository.UserRepository;
import com.example.sweater.service.TodoService;
import com.example.sweater.service.UserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public Todo createUserTodo(Long userId, CreateTodoBean createTodoBean) {

        if (userRepository.findById(userId).isPresent()) {
            // throw exception
        }

        createTodoBean.setUserId(userId);

        return todoService.createTodo(createTodoBean);
    }

    @Override
    public Todo updateUserTodo(Long todoId, CreateTodoBean createTodoBean) {

        if (!userRepository.findById(todoId).isPresent()) {
            // throw exception Don't exist
        }

        createTodoBean.setUserId(todoId);

        return todoService.updateTodo(createTodoBean);
    }

    @Override
    public Todo deleteById(Long id) {
        if (!userRepository.findById(id).isPresent()) {
            // throw exception Don't exist
        }

        return todoService.deleteById(id);
    }

    @Override
    public List<UserWithNumberOfTodos> numberOfTodos() {
        List<User> temp = findAll();
        List<UserWithNumberOfTodos> temp1 = new ArrayList<>();

        temp.forEach(user -> {
            UserWithNumberOfTodos usr = new UserWithNumberOfTodos();
            usr.setNrOfTodos(todoService.countTodoForUser(user.getId()));
            usr.setUserName(user.getUsername());

            temp1.add(usr);
        });
        return temp1;
    }
}
