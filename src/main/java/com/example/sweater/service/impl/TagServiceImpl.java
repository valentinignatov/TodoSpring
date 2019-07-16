package com.example.sweater.service.impl;

import com.example.sweater.model.Tag;
import com.example.sweater.repository.TagRepository;
import com.example.sweater.service.TagService;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
