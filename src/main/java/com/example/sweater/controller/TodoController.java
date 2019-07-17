package com.example.sweater.controller;

import com.example.sweater.model.Todo;
import com.example.sweater.service.TodoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/todos")
public class TodoController {

    private TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping(path = "/all")
    public ResponseEntity<List<Todo>> findAllUsers() {
        return new ResponseEntity<>(todoService.findAll(), HttpStatus.OK);
    }

    // find by user id
    @GetMapping(value = "/{id}")
    public ResponseEntity<Optional<Todo>> findById(@PathVariable( "id" ) Long id) {
        return new ResponseEntity<>(todoService.findById(id), HttpStatus.OK);
    }

    // update todo

    // delete todo
}
