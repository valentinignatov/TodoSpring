package com.example.sweater.service;

import com.example.sweater.model.Tag;

import java.util.List;
import java.util.Optional;

public interface TagService {

    List<Tag> findAll();

    List<Tag> findAllByName(String tagName);

    Optional<Tag> findById(Long id);

    void addTagForTodo(Long tagId, Long todoId);

    void updateTagforTodo(Long tagId, Long todoId);

    void deleteByTodoId(Long id);

    Long findIdByName(String tag);
}
