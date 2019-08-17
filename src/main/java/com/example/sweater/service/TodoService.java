package com.example.sweater.service;

import com.example.sweater.bean.CreateTodoBean;
import com.example.sweater.model.Todo;

import java.util.List;
import java.util.Optional;


public interface TodoService {

    Todo save(Todo todo);

    List<Todo> findAll();

    Todo findById(Long id);

    Todo createTodo(CreateTodoBean createTodoBean);

    Todo updateTodo(CreateTodoBean createTodoBean);

    Todo deleteById(Long id);

    Long countTodoForUser(Long userId);

    List<Todo> findByTextLike(String text);

    Optional<Todo> checkTodoForUser(Long todoId, Long userId);

//    List<Todo> findByTagLike(String tag);

    List<Todo> search(String textToFind, String tagName);
}
