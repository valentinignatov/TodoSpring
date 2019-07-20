package com.example.sweater.service.impl;

import com.example.sweater.bean.CreateTodoBean;
import com.example.sweater.model.Tag;
import com.example.sweater.model.Todo;
import com.example.sweater.repository.TodoRepository;
import com.example.sweater.service.TagService;
import com.example.sweater.service.TodoService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodoServiceImpl implements TodoService {

    private TodoRepository todoRepository;

    private TagService tagService;

    public TodoServiceImpl(TodoRepository todoRepository,
                           TagService tagService) {
        this.todoRepository = todoRepository;
        this.tagService = tagService;
    }

    @Override
    public List<Todo> findAll() {
        return todoRepository.findAll();
    }

    @Override
    public Optional<Todo> findById(Long id) {
        return todoRepository.findById(id);
    }

    @Override
    public Todo save(Todo todo) { return todoRepository.save(todo);}

    @Override
    public Todo createTodo(CreateTodoBean createTodoBean) {
        Optional<String> foundTodo = todoRepository.findByText(createTodoBean.getText());
        Optional<Tag> foundTag = tagService.findById(createTodoBean.getTagId());

        Todo savedTodo = new Todo();
        savedTodo.setUserId(createTodoBean.getUserId());
        savedTodo.setText(createTodoBean.getText());

        if (foundTodo.isPresent()) {
            //Throw  exept

        } else if (foundTag.isPresent()) {
            todoRepository.save(savedTodo);

            tagService.addTagForTodo(createTodoBean.getTagId(),todoRepository.getIdByText(savedTodo.getText()));

            savedTodo.setText(savedTodo.getText()+" SUCCESFULY ADDED");
        }

        // check if exists by id

        savedTodo.toString();

        return savedTodo;
    }

    @Override
    public Todo updateTodo(CreateTodoBean createTodoBean) {
        Optional<Tag> foundTag = tagService.findById(createTodoBean.getTagId());

        Todo updateTodo = new Todo();
        updateTodo.setUserId(createTodoBean.getUserId());
        updateTodo.setText(createTodoBean.getText());


        if (todoRepository.existsById(createTodoBean.getUserId()) && foundTag.isPresent()) {
            tagService.updateTagforTodo(createTodoBean.getTagId(), createTodoBean.getUserId());
        }

        if (todoRepository.existsById(createTodoBean.getUserId()) && foundTag.isPresent()) {
            //createTodoBean.getUserId() contains todoId

            todoRepository.updateById(createTodoBean.getText(), createTodoBean.getUserId());
        }
        return updateTodo;
    }

    @Override
    public Todo deleteById(Long id) {
        tagService.deleteByTodoId(id);

        todoRepository.deleteById(id);

        return null;
    }
}
