package com.example.sweater.service;

import com.example.sweater.model.Tag;

import java.util.List;

public interface TagService {

    /**
     * find all tag list
     * @return
     */
    List<Tag> findAll();

}
