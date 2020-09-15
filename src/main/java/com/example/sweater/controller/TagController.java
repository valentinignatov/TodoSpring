package com.example.sweater.controller;

import com.example.sweater.model.Tag;
import com.example.sweater.service.TagService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "api/tags")
public class TagController {

    private TagService tagService;

    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @RequestMapping(path = "/allTags")
    public ResponseEntity<List<Tag>> findAll() {
        return new ResponseEntity<>(tagService.findAll(), HttpStatus.OK);
    }
}
