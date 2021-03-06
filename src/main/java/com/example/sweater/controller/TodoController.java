package com.example.sweater.controller;

import com.example.sweater.model.Todo;
import com.example.sweater.service.TodoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/todos")
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
    public ResponseEntity<Todo> findById(@PathVariable( "id" ) Long id) {
        return new ResponseEntity<>(todoService.findById(id), HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Todo>> search(
            @RequestParam(name = "textToFind", required = false) String textToFind,
            @RequestParam(name = "tagName", required = false) String tagName) {
        return new ResponseEntity<>(todoService.search(textToFind, tagName), HttpStatus.OK);
    }
}
