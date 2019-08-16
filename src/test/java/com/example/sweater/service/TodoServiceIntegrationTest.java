package com.example.sweater.service;

import com.example.sweater.bean.CreateTodoBean;
import com.example.sweater.model.Todo;
import com.example.sweater.repository.TodoRepository;
import com.example.sweater.service.impl.FileReaderServiceImpl;
import lombok.val;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TodoServiceIntegrationTest {

    @Before
    public void before() {
        FileReaderService fileReaderService = new FileReaderServiceImpl();
        fileReaderService.initDatabase();
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Autowired
    private TodoService todoService;

    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private UserService userService;

    @Test
    public void findAll() {
        List<Todo> todos = todoService.findAll();

        assertNotNull(todos);
        assertNotNull(todos.stream().findFirst().get().getId());
        assertEquals(todos.stream().findFirst().get().getId().longValue(), 1L);
    }

    @Test
    public void findById() {
        Todo foundTodo = todoService.findById(1L);

        assertNotNull(foundTodo);
        assertNotNull(foundTodo.getId());
        assertEquals(foundTodo.getId().longValue(), 1L);
        assertEquals(foundTodo.getText(),"de maturat");
    }

    @Test
    public void save() {
        Todo creatingTodo = Todo.builder()
                .userId(1L)
                .text("Testing save() method")
                .build();

        Todo createdTodo = todoService.save(creatingTodo);

        assertNotNull(createdTodo);
        assertNotNull(createdTodo.getId());
        assertEquals(createdTodo.getUserId(), creatingTodo.getUserId());
        assertEquals(createdTodo.getText(), creatingTodo.getText());
    }

    @Test
    public void createTodo() {
        CreateTodoBean createTodoBean = CreateTodoBean.builder()
                .userId(1L)
                .text("Testing createTodo() method")
                .tagId(1L)
                .build();

        Todo createdTodo = todoService.createTodo(createTodoBean);

        assertNotNull(createdTodo);
        assertNotNull(createdTodo.getUserId());
        assertEquals(createdTodo.getUserId(), createTodoBean.getUserId());
        assertEquals(createdTodo.getText(), createTodoBean.getText());
    }

    @Test
    public void updateTodo() {
        val createTodoBean = CreateTodoBean.builder()
                .userId(1L)
                .text("Testing updateTodo() method")
                .tagId(1L)
                .build();

        Todo createdTodo = todoService.updateTodo(createTodoBean);

        assertNotNull(createdTodo);
        assertNotNull(createdTodo.getUserId());
        assertEquals(createdTodo.getUserId(), createTodoBean.getUserId());
        assertEquals(createdTodo.getText(), createTodoBean.getText());
    }

    @Test
    public void deleteById() {

        val countTagBefore = todoRepository.countTodosToTags();
        val countTodoBefore = todoRepository.count();

        userService.deleteById(1L, 1L);

        val countTagAfter = todoRepository.countTodosToTags();
        val countTodoAfter = todoRepository.count();

        assertTrue(countTagBefore > countTagAfter);
        assertTrue(countTodoBefore > countTodoAfter);
    }

    @Test
    public void countTodoForUser() {
        val count = todoService.countTodoForUser(1L);

        assertNotNull(count);
        assertTrue(count == 3L);
    }

    @Test
    public void findByTextLike() {
        val expectedTodo = Todo.builder()
                .id(4L)
                .userId(2L)
                .text("de reparat tractorul")
                .build();

        val foundTodos = todoService.findByTextLike("de reparat");

        assertNotNull(foundTodos);
        assertEquals(foundTodos.get(0).getId(), expectedTodo.getId());
        assertEquals(foundTodos.get(0).getUserId(),expectedTodo.getUserId());
        assertEquals(foundTodos.get(0).getText(), expectedTodo.getText());
    }

    @Test
    public void checkTodoForUser() {
        val todoId = 1L;
        val userId = 1L;

        val checkTodo = todoService.checkTodoForUser(todoId, userId);

        assertNotNull(checkTodo);
        assertEquals(checkTodo.get().getId().longValue(), todoId);
        assertEquals(checkTodo.get().getUserId().longValue(), userId);
    }

    @Test
    public void findByTagLike() {
        val id = 1L;
        val userId = 1L;
        val text = "de maturat";

        val tag = "not";
        val todos = todoService.findByTagLike(tag);

        assertNotNull(todos);
        assertEquals(todos.get(0).getId().longValue(), id);
        assertEquals(todos.get(0).getUserId().longValue(), userId);
        assertEquals(todos.get(0).getText(), text);
    }

    @Test
    public void search() {
        val id = 6L;
        val userId = 2L;
        val text = "de prasit";

        val todoText = "de pra";
        val tag = "not";
        val todos = todoService.search(text, tag);

        assertNotNull(todos);
        assertEquals(todos.get(0).getId().longValue(), id);
        assertEquals(todos.get(0).getUserId().longValue(), userId);
        assertEquals(todos.get(0).getText(), text);
    }
}