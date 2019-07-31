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
        String testSql = "select * from todos";

        if (textToFind != null && tagName != null) {
            testSql = "select * from todos left join todos_to_tags on todos.id = todos_to_tags.todo_id left join tags on todos_to_tags.tag_id = tags.id where tags.tag_name like '%" + tagName + "%' and text like '%" + textToFind + "%'";
        }

        else if (textToFind != null) {
            testSql = "select * from todos where text like '%" + textToFind + "%'";
        }

        else if (tagName != null) {
            testSql = "select * from todos" +
                    " left join todos_to_tags on todos.id = todos_to_tags.todo_id" +
                    " left join tags on todos_to_tags.tag_id = tags.id" +
                    " where tags.tag_name like '%" + tagName + "%'";
        }
        Query query = entityManager.createNativeQuery(testSql, Todo.class);

        return query.getResultList();
    }
}
