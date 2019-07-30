package com.example.sweater.repository.custom;

import com.example.sweater.model.Todo;

import java.util.List;

public interface TodoCustomRepository {

    List<Todo> search(String textToFind, String tagName);
}
