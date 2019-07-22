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
    public ResponseEntity<List<Todo>> findAll() {
        return new ResponseEntity<>(todoService.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Optional<Todo>> findById(@PathVariable( "id" ) Long id) {
        return new ResponseEntity<>(todoService.findById(id), HttpStatus.OK);
    }

    @GetMapping(value = "/findByText/{textToFind}")
    public ResponseEntity<List<Todo>> searchByText(@PathVariable("textToFind") String text) {
        return new ResponseEntity<>(todoService.findByTextLike(text), HttpStatus.OK);
    }
}
