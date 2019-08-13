package com.example.sweater.service;

import com.example.sweater.model.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserServiceIntegrationTest {

    @Autowired
    private UserService userService;

    @Test
    public void findAll() {
        List<User> users = userService.findAll();

        Assert.assertNotNull(users);
        Assert.assertNotNull(users.stream().findFirst().get().getId());
        Assert.assertEquals(users.stream().findFirst().get().getId().longValue(), 1L);
    }

    @Test
    public void findById() {

    }

    @Test
    public void createUserTodo() {
    }

    @Test
    public void updateUserTodo() {
    }

    @Test
    public void deleteById() {
    }

    @Test
    public void numberOfTodos() {
    }

    @Test
    public void findAllUserBean() {
    }
}