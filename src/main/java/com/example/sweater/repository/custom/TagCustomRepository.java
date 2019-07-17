package com.example.sweater.repository.custom;

import com.example.sweater.model.Tag;

import java.util.List;

public interface TagCustomRepository {

    List<Tag> findAllBy();
}
