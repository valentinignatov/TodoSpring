package com.example.sweater.service;

import com.example.sweater.bean.CreateTodoBean;
import com.example.sweater.model.Todo;
import com.example.sweater.model.User;
import com.example.sweater.repository.TodoRepository;
import com.example.sweater.service.impl.FileReaderServiceImpl;
import lombok.val;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserServiceIntegrationTest {

    @Before
    public void before() {
        FileReaderService fileReaderService = new FileReaderServiceImpl();
        fileReaderService.initDatabase();
    }

    @Autowired
    private UserService userService;

    @Autowired
    private TodoService todoService;

    @Autowired
    private TodoRepository todoRepository;

    @Test
    public void findAll() {
        List<User> users = userService.findAll();

        Assert.assertNotNull(users);
        Assert.assertNotNull(users.stream().findFirst().get().getId());
        Assert.assertEquals(users.stream().findFirst().get().getId().longValue(), 1L);
    }

    @Test
    public void findById() {
        User foundUser = userService.findById(1L);

        Assert.assertNotNull(foundUser);
        Assert.assertNotNull(foundUser.getId());
        Assert.assertEquals(foundUser.getId().longValue(), 1L);
        Assert.assertEquals(foundUser.getUsername(),"victor");
    }

    @Test
    public void createUserTodo() {
        Long userId = 1L;
        val createTodoBean = CreateTodoBean.builder()
                .userId(userId)
                .text("Creating a testing Todo for User \"Victor\"")
                .tagId(1L)
                .build();

        Todo createdTodo = userService.createUserTodo(userId, createTodoBean);

        Assert.assertNotNull(createdTodo);
        Assert.assertNotNull(createdTodo.getId());
        Assert.assertEquals(createdTodo.getUserId(), createTodoBean.getUserId());
        Assert.assertEquals(createdTodo.getText(), createTodoBean.getText());
    }

    @Test
    public void updateUserTodo() {
        Long userId = 1L;
        val createTodoBean = CreateTodoBean.builder()
                .userId(userId)
                .text("Update Testing Todo for User Victor")
                .tagId(2L)
                .build();

        Todo updatedTodo = userService.updateUserTodo(userId, 1L,createTodoBean);

        Assert.assertNotNull(updatedTodo);
        Assert.assertEquals(updatedTodo.getUserId(), createTodoBean.getUserId());
        Assert.assertEquals(updatedTodo.getText(), createTodoBean.getText());
    }

    @Test
    public void deleteById() {

        val countBefore = todoRepository.count();

        userService.deleteById(1L, 1L);

        val countAfter = todoRepository.count();

        Assert.assertTrue(countBefore > countAfter);
    }

    @Test
    public void findAllUserBean() {
        val result = userService.findAllUserBean();

        assertNotNull(result);
        assertEquals(result.get(0).getId().longValue(), 1L);
        assertEquals(result.get(0).getUsername(),"victor");
        assertEquals(result.get(0).getNrOfTodos().longValue(), 3L);
    }
}