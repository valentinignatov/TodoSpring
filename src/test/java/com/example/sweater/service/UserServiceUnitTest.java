package com.example.sweater.service;

import com.example.sweater.model.User;
import com.example.sweater.repository.UserRepository;
import lombok.val;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;

public class UserServiceUnitTest {

    @Before
    //de initializat baza din nou

    @Autowired
    private UserService userService;

    @Test
    public void findAll() {

        val mockUserRepository = Mockito.mock(UserRepository.class);
        val mockList = Collections.singletonList(Mockito.mock(User.class));

        Mockito.verify(userService, Mockito.times(1));

        Mockito.when(mockUserRepository.findAll()).thenReturn(mockList);

        val results = userService.findAll();

        Assert.assertNotNull(results);
        Assert.assertEquals(results, mockList);
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
