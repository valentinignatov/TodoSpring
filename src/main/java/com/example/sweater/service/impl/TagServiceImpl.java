package com.example.sweater.service.impl;

import com.example.sweater.model.Tag;
import com.example.sweater.repository.TagRepository;
import com.example.sweater.service.TagService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TagServiceImpl implements TagService {

    private TagRepository tagRepository;

    public TagServiceImpl(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    @Override
    public List<Tag> findAll() {
        return tagRepository.findAll();
    }

    @Override
    public Optional<Tag> findById(Long id) {return tagRepository.findById(id); }

    @Override
    public void addTagForTodo(Long tagId, Long todoId) { tagRepository.addTagForTodo(tagId, todoId); }

    @Override
    public void updateTagforTodo(Long tagId, Long todoId) { tagRepository.updateTagforTodo(tagId, todoId); }

    @Override
    public void deleteByTodoId(Long id) { tagRepository.deleteByTodoId(id); }
}
