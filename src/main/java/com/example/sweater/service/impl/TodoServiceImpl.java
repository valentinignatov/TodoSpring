package com.example.sweater.service.impl;

import com.example.sweater.repository.TodoRepository;
import com.example.sweater.service.TodoService;
import com.example.sweater.model.Todo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoServiceImpl implements TodoService {

    private TodoRepository todoRepository;

    public TodoServiceImpl(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @Override
    public List<Todo> findAll() {
        return todoRepository.findAll();
    }
}
