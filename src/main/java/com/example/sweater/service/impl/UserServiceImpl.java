package com.example.sweater.service.impl;

import com.example.sweater.bean.CreateTodoBean;
import com.example.sweater.bean.UserBean;
import com.example.sweater.model.Todo;
import com.example.sweater.model.User;
import com.example.sweater.repository.UserRepository;
import com.example.sweater.service.TodoService;
import com.example.sweater.service.UserService;
import lombok.val;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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
    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
    }

    @Override
    public Todo createUserTodo(Long userId, CreateTodoBean createTodoBean) {
        createTodoBean.setUserId(userId);
        return todoService.createTodo(createTodoBean);
    }

    @Override
    public Todo updateUserTodo(Long userId, Long todoId, CreateTodoBean createTodoBean) {

        findById(userId);

        val foundTodo = todoService.findById(todoId);

        if (!Objects.equals(foundTodo.getUserId(), userId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "This user don't have this TODO");
        }

        createTodoBean.setUserId(todoId);

        return todoService.updateTodo(createTodoBean);
    }

    @Override
    public Todo deleteById(Long userId, Long todoId) {

        findById(userId);

        val foundTodo = todoService.findById(todoId);

        if (!Objects.equals(foundTodo.getUserId(), userId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "This user don't have this TODO");
        }

        return todoService.deleteById(todoId);
    }

    @Override
    public List<UserBean> findAllUserBean() {
       return userRepository
                .findAll()
                .stream()
                .map(it ->
                        UserBean
                                .builder()
                                .id(it.getId())
                                .username(it.getUsername())
                                .nrOfTodos(todoService.countTodoForUser(it.getId()))
                                .createdOn(it.getCreatedOn())
                                .build()
                )
               .collect(Collectors.toList());
    }
}
