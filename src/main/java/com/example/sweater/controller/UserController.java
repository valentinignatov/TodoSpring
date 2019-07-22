package com.example.sweater.controller;


import com.example.sweater.bean.CreateTodoBean;
import com.example.sweater.bean.UserWithNumberOfTodos;
import com.example.sweater.model.Todo;
import com.example.sweater.model.User;
import com.example.sweater.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<List<User>> findAll() {
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Optional<User>> findById(@PathVariable( "id" ) Long id) {
        return new ResponseEntity<>(userService.findById(id), HttpStatus.OK);
    }

    @GetMapping(path = "/todos")
    public ResponseEntity<List<UserWithNumberOfTodos>> countTodosForUser() {
        return new ResponseEntity<>(userService.numberOfTodos(), HttpStatus.OK);
    }

    @PostMapping(value = "/{userId}/todos/add")
    public ResponseEntity<Todo> createTodo(@PathVariable("userId") Long userId,
                                           @RequestBody CreateTodoBean createTodoBean) {
        return new ResponseEntity<>(userService.createUserTodo(userId, createTodoBean), HttpStatus.OK);
    }

    @PutMapping(value = {"/{todoId}/todos/update"})
    public ResponseEntity<Todo> updateTodo(@PathVariable("todoId") Long todoId,
                                           @RequestBody CreateTodoBean createTodoBean) {
        return new ResponseEntity<>(userService.updateUserTodo(todoId, createTodoBean), HttpStatus.OK);
    }

    @DeleteMapping(value = {"/{todoId}/todos/delete"})
    public ResponseEntity<Todo> delete(@PathVariable("todoId") Long todoId) {
        return new ResponseEntity<>(userService.deleteById(todoId), HttpStatus.OK);
    }
}
