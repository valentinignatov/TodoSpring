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
public class TodoRepositoryIntegrationTest {

    @Autowired
    TodoRepository todoRepository;

    @Before
    public void before() {
        FileReaderService fileReaderService = new FileReaderServiceImpl();
        fileReaderService.initDatabase();
    }

    @Test
    public void findByText() {
        val todoText = "de maturat";

        val result = todoRepository.findByText(todoText);

        assertNotNull(result);
        assertEquals(result.get(), todoText);
    }

    @Test
    public void updateById() {
        val todoId = 1L;
        val textForUpdate = "Testing Update method";

        val verifyBefore = todoRepository.findById(todoId);

        todoRepository.updateById(textForUpdate, todoId);

        val verifyAfter = todoRepository.findById(todoId);

        assertNotNull(verifyBefore);
        assertNotNull(verifyAfter);
        assertNotEquals(verifyBefore, verifyAfter);
    }

    @Test
    public void countTodoForUser() {
        val exprectedCountResult = 3L;
        val todoId = 1L;

        val result = todoRepository.countTodoForUser(todoId);

        assertNotNull(result);
        assertTrue(result == exprectedCountResult);
    }

    @Test
    public void findByTextLike() {
        val text = "de gasit";

        val result = todoRepository.findByTextLike(text);

        assertNotNull(result);
        assertTrue(result.get(0).getText().contains(text));
    }

    @Test
    public void checkTodoForUser() {
        val todoId = 1L;
        val userId = 1L;

        val result = todoRepository.checkTodoForUser(todoId, userId);

        assertNotNull(result);
        assertSame(result.get().getId(), todoId);
        assertSame(result.get().getUserId(), userId);
    }

    @Test
    public void verifyTagIdByTodoId() {
        val todoId = 9L;
        val result = todoRepository.verifyTagIdByTodoId(todoId);

        assertNotNull(result);
    }

    @Test
    public void countTodosToTags() {
        val result = todoRepository.countTodosToTags();

        assertNotNull(result);
    }
}