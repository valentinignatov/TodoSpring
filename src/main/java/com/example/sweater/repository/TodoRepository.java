package com.example.sweater.repository;

import com.example.sweater.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface TodoRepository extends JpaRepository<Todo, Long> {
    @Query(value = "select max(id) from todos",nativeQuery = true)
    Long findMaxId();

    @Query(value = "select text from todos where text = ?1", nativeQuery = true)
    Optional<String> findByText (String text);

    @Query(value = "select id from todos where text = ?1", nativeQuery = true)
    Long getIdByText (String text);

    @Modifying
    @Transactional
    @Query(value = "update todos set text = ?1 where id = ?2", nativeQuery = true)
    void updateById (String updateTodo, Long todoId);

    @Query(value = "select count (text) from todos where user_id = ?1", nativeQuery = true)
    Long countTodoForUser(Long userId);

    @Query(value = "select * from todos where text like %?1%", nativeQuery = true)
    List<Todo> findByTextLike(String text);

    @Query("select p from Todo p where id = ?2 and userId = ?1")
    Optional<Todo> checkTodoForUser(Long todoId, Long userId);

    @Query(value = "select todos.id, todos.user_id, todos.text, todos.created_on, todos.updated_on from todos, todos_to_tags " +
            "where todos_to_tags.tag_id = ?1 and todos.id = todos_to_tags.todo_id", nativeQuery = true)
    ArrayList<Optional<Todo>> findByTagId(Long id);
}
