package com.example.sweater.repository.custom.impl;

import com.example.sweater.model.Todo;
import com.example.sweater.repository.custom.TodoCustomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository
public class TodoCustomRepositoryImpl implements TodoCustomRepository {

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Todo> search(String textToFind, String tagName) {

        String sql = "SELECT * FROM todos ";

        Query query = entityManager.createNativeQuery(sql);

        if (textToFind != null) {
            // todo: build query

            sql = sql + " where text LIKE ?";
            query.setParameter(1, textToFind);

        }

        if (tagName != null) {
            // todo: build query
            sql = sql + " join todo_to_tags on todo_to_tags.todo_id = tag.id " +
                    "where tag.name = ?";

            query.setParameter(2, tagName);

        }

        return query.getResultList();
    }
}
