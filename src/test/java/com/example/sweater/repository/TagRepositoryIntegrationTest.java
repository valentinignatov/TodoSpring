package com.example.sweater.repository;

import com.example.sweater.service.FileReaderService;
import com.example.sweater.service.impl.FileReaderServiceImpl;
import lombok.val;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TagRepositoryIntegrationTest {

    @Autowired
    TagRepository tagRepository;

    @Autowired
    TodoRepository todoRepository;

    @Before
    public void before() {
        FileReaderService fileReaderService = new FileReaderServiceImpl();
        fileReaderService.initDatabase();
    }

    @Test
    public void addTagForTodo() {
        val tagId = 1L;
        val todoId = 1L;
        val countBefore = todoRepository.countTodosToTags();

        tagRepository.addTagForTodo(tagId, todoId);

        val countAfter = todoRepository.countTodosToTags();

        assertNotNull(countAfter);
        assertNotNull(countBefore);
        assertTrue(countAfter > countBefore);
    }

    @Test
    public void updateTagforTodo() {
        val tagId = 3L;
        val todoId = 1L;
        val verifyBefore = todoRepository.verifyTagIdByTodoId(todoId);

        tagRepository.updateTagforTodo(tagId, todoId);

        val verifyAfter = todoRepository.verifyTagIdByTodoId(todoId);

        assertNotNull(verifyBefore);
        assertNotNull(verifyAfter);
        assertNotEquals(verifyBefore, verifyAfter);
    }

    @Test
    public void deleteByTodoId() {
        val todoId = 1L;
        val countBefore = todoRepository.countTodosToTags();

        tagRepository.deleteByTodoId(todoId);

        val countAfter = todoRepository.countTodosToTags();

        assertNotNull(countAfter);
        assertNotNull(countBefore);
        assertTrue(countAfter < countBefore);
    }
}