package com.example.sweater.controller;

import com.example.sweater.model.Tag;
import com.example.sweater.service.TagService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/todo")
public class TagController {

    private TagService tagService;

    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @GetMapping("/search")
    public List<Tag> findAll(@RequestParam (name = "textToFind", required = false) String textToFind,
                             @RequestParam (name = "tagName", required = false) String tagName) {
        return new ResponseEntity<>(tagService.findAll(textToFind, tagName), HttpStatus.OK);
    }
}
