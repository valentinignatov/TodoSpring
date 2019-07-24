package com.example.sweater.service.impl;

import com.example.sweater.bean.CreateTodoBean;
import com.example.sweater.bean.UserWithNumberOfTodos;
import com.example.sweater.model.Todo;
import com.example.sweater.model.User;
import com.example.sweater.repository.UserRepository;
import com.example.sweater.service.TodoService;
import com.example.sweater.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.sql.SQLException;
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
        createTodoBean.setUserId(userId);

        return todoService.createTodo(createTodoBean);
    }

    @Override
    public Todo updateUserTodo(Long userId, Long todoId, CreateTodoBean createTodoBean) {
        System.out.println("todoService.checkTodoForUser(userId, todoId)"+todoService.checkTodoForUser(userId, todoId));
        if (!userRepository.findById(userId).isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
        if (!todoService.checkTodoForUser(userId, todoId).isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "This user don't have this TODO");
        }

        createTodoBean.setUserId(todoId);

        return todoService.updateTodo(createTodoBean);
    }

    @Override
    public Todo deleteById(Long userId, Long todoId) {
        if (!userRepository.findById(userId).isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
        if (!todoService.checkTodoForUser(userId, todoId).isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "This user don't have this TODO");
        }

        return todoService.deleteById(todoId);
    }

    @Override
    public List<UserWithNumberOfTodos> numberOfTodos() {
        List<User> userList = findAll();
        List<UserWithNumberOfTodos> userWithTodoNr = new ArrayList<>();

        userList.forEach(user -> {
            UserWithNumberOfTodos usr = new UserWithNumberOfTodos();
            usr.setNrOfTodos(todoService.countTodoForUser(user.getId()));
            usr.setUserName(user.getUsername());

            userWithTodoNr.add(usr);
        });
        return userWithTodoNr;
    }
}
