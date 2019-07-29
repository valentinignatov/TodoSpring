package com.example.sweater.service.impl;

import com.example.sweater.bean.CreateTodoBean;
import com.example.sweater.model.Todo;
import com.example.sweater.repository.TodoRepository;
import com.example.sweater.service.TagService;
import com.example.sweater.service.TodoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class TodoServiceImpl implements TodoService {

    private TodoRepository todoRepository;

    private TagService tagService;

    public TodoServiceImpl(TodoRepository todoRepository,
                           TagService tagService) {
        this.todoRepository = todoRepository;
        this.tagService = tagService;
    }

    @Override
    public List<Todo> findAll() { return todoRepository.findAll(); }

    @Override
    public Optional<Todo> findById(Long id) {
        return todoRepository.findById(id);
    }

    @Override
    public Todo save(Todo todo) { return todoRepository.save(todo);}

    @Override
    public Todo createTodo(CreateTodoBean createTodoBean) {

        Todo savedTodo = new Todo();
        savedTodo.setUserId(createTodoBean.getUserId());
        savedTodo.setText(createTodoBean.getText());

        if (todoRepository.findByText(createTodoBean.getText()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Todo allready exists");
        }
        if (!tagService.findById(createTodoBean.getTagId()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No such tag to add for todo");
        }
        todoRepository.save(savedTodo);

        tagService.addTagForTodo(createTodoBean.getTagId(),todoRepository.getIdByText(savedTodo.getText()));

        savedTodo.setText(savedTodo.getText());

        return savedTodo;
    }

    @Override
    public Todo updateTodo(CreateTodoBean createTodoBean) {

        Todo updateTodo = new Todo();
        updateTodo.setUserId(createTodoBean.getUserId());
        updateTodo.setText(createTodoBean.getText());

        if (!todoRepository.existsById(createTodoBean.getUserId())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No such todo to update");
        }
        if (!tagService.findById(createTodoBean.getTagId()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No such tag to update");
        }
        //createTodoBean.getUserId() contains todoId
        tagService.updateTagforTodo(createTodoBean.getTagId(), createTodoBean.getUserId());
        todoRepository.updateById(createTodoBean.getText(), createTodoBean.getUserId());

        return updateTodo;
    }

    @Override
    public Todo deleteById(Long id) {
        tagService.deleteByTodoId(id);

        todoRepository.deleteById(id);

        return null;
    }

    @Override
    public Long countTodoForUser(Long userId) {
        return todoRepository.countTodoForUser(userId);
    }

    @Override
    public List<Todo> findByTextLike(String text) {
        return todoRepository.findByTextLike(text);
    }

    @Override
    public Optional<Todo> checkTodoForUser(Long todoId, Long userId) { return todoRepository.checkTodoForUser(todoId, userId); }

    @Override
    public List<Todo> findByTagLike(String tag) {
        Long id = tagService.findIdByName(tag);

        return todoRepository.findByTagId(id);
    }

    @Override
    public List<Todo> search(String textToFind, String tagName) {
        List<Todo> finalTodos = new ArrayList<>();

        if (!todoRepository.findByTextLike(textToFind).isEmpty() && !tagService.findAllByName(tagName).isEmpty()) {
            List<Todo> todos = new ArrayList<>();

            for (int i = 0; i < todoRepository.findByTagId(tagService.findIdByName(tagName)).size(); i++) {
                int finalI = i;
                todoRepository.findByTextLike(textToFind).forEach(todo -> {
                    if (todoRepository.findByTagId(tagService.findIdByName(tagName)).get(finalI).
                            equals(todo)) {
                        todos.add(todo);
                    }
                });
            }
            finalTodos = todos;
        }
        else if (!todoRepository.findByTextLike(textToFind).isEmpty())
            finalTodos = todoRepository.findByTextLike(textToFind);

        else if (!tagService.findAllByName(tagName).isEmpty()) {
            finalTodos = todoRepository.findByTagId(tagService.findIdByName(tagName));
        }
        return finalTodos;
    }
}
