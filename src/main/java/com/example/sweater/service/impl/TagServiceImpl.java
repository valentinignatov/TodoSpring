package com.example.sweater.service.impl;

import com.example.sweater.model.Tag;
import com.example.sweater.repository.TagRepository;
import com.example.sweater.service.TagService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class TagServiceImpl implements TagService {

    private TagRepository tagRepository;

    public TagServiceImpl(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    @Override
    public List<Tag> findAll() { return tagRepository.findAll(); }

    @Override
    public List<Tag> findAllByName(String tagName) { return tagRepository.findByNameLike(tagName); }

    @Override
    public Optional<Tag> findById(Long id) {return tagRepository.findById(id); }

    @Override
    public void addTagForTodo(Long tagId, Long todoId) { tagRepository.addTagForTodo(tagId, todoId); }

    @Override
    public void updateTagforTodo(Long tagId, Long todoId) { tagRepository.updateTagforTodo(tagId, todoId); }

    @Override
    public void deleteByTodoId(Long id) { tagRepository.deleteByTodoId(id); }

    @Override
    public Long findIdByName(String tag) {
        if (tagRepository.findIdByName(tag).size()>1) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Found more then 1 tag");
        }
        if (tagRepository.findIdByName(tag).isEmpty() && !tagRepository.findByNameLike(tag).equals("XXXXXXX")) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No such tag found");
        }

        Long id = tagRepository.findIdByName(tag).get(0);
        return id;
    }
}
