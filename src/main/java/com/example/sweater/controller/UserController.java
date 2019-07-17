package com.example.sweater.controller;


import com.example.sweater.bean.CreateTodoBean;
import com.example.sweater.model.Todo;
import com.example.sweater.model.User;
import com.example.sweater.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/users")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = "/all")
    public ResponseEntity<List<User>> findAllUsers() {
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Optional<User>> findById(@PathVariable( "id" ) Long id) {
        return new ResponseEntity<>(userService.findById(id), HttpStatus.OK);
    }

    @GetMapping(path = "/todos")
    public ResponseEntity<List<User>> ff() {
        List<User> temp = new ArrayList<>();
        temp = userService.findAll();

        temp.forEach(user -> {
            userService.howMuchTodos(user.toString());
        });
        return null;
    }

    // add list of users with hoe many todos they have

    @PostMapping(value = "/{userId}/todos/add")
    public ResponseEntity<Todo> createTodo(@RequestParam("userId") Long userId,
                                           @RequestBody CreateTodoBean createTodoBean) {
        return new ResponseEntity<>(userService.createUserTodo(userId, createTodoBean), HttpStatus.OK);
    }

    // update todo
    // delete todo


}
