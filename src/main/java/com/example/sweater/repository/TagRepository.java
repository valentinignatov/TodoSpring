package com.example.sweater.repository;

import com.example.sweater.model.Tag;
import com.example.sweater.repository.custom.TagCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface TagRepository extends JpaRepository<Tag, Long>, TagCustomRepository {
    TagCustomRepository tagCustomRepository = null;

    @Modifying
    @Transactional
    @Query(value = "insert into todos_to_tags(tag_id, todo_id) values (?1, ?2)", nativeQuery = true)
    void addTagForTodo(Long tagId, Long todoId);

    @Modifying
    @Transactional
    @Query(value = "update todos_to_tags set tag_id = ?1 where todo_id = ?2", nativeQuery = true)
    void updateTagforTodo(Long tagId, Long todoId);

    @Modifying
    @Transactional
    @Query(value = "delete from todos_to_tags where todo_id = ?1", nativeQuery = true)
    void deleteByTodoId(Long id);

    List<Long> findIdByName(String tag);
}
