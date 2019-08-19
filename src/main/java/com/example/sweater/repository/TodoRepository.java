package com.example.sweater.repository;

import com.example.sweater.model.Todo;
import com.example.sweater.repository.custom.TodoCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface TodoRepository extends JpaRepository<Todo, Long>, TodoCustomRepository {

    @Query(value = "select text from todos where text = ?1", nativeQuery = true)
    Optional<String> findByText(String text);

    @Modifying
    @Transactional
    @Query(value = "update todos set text = ?1 where id = ?2", nativeQuery = true)
    void updateById(String updateTodo, Long todoId);

    @Query(value = "select count (text) from todos where user_id = ?1", nativeQuery = true)
    Long countTodoForUser(Long userId);

    @Query(value = "select * from todos where text like %?1%", nativeQuery = true)
    List<Todo> findByTextLike(String text);

    @Query("select p from Todo p where id = ?2 and userId = ?1")
    Optional<Todo> checkTodoForUser(Long todoId, Long userId);

    @Query(value = "select tag_id from todos_to_tags where todo_id = ?1", nativeQuery = true)
    List<Long> verifyTagIdByTodoId(Long todoId);

    @Query(value = "select count(*) from todos_to_tags", nativeQuery = true)
    Long countTodosToTags();
}
