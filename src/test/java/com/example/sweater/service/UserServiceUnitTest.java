package com.example.sweater.service;

import com.example.sweater.model.User;
import com.example.sweater.repository.UserRepository;
import com.example.sweater.service.impl.FileReaderServiceImpl;
import com.example.sweater.service.impl.UserServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceUnitTest {

    @Before
    public void before() {
        FileReaderService fileReaderService = new FileReaderServiceImpl();
        fileReaderService.initDatabase();
    }

    @Mock
    private UserRepository userRepository;

    @Mock
    private TodoService todoService;

    @InjectMocks
    UserServiceImpl userService;

    @Test
    public void findAll() {
//
//        val mockUserRepository = Mockito.mock(UserRepository.class);
//        val mockList = Collections.singletonList(Mockito.mock(User.class));
//
//        Mockito.when(mockUserRepository.findAll()).thenReturn(mockList);
//
//        val results = userService.findAll();
//
//        System.out.println("mockList "+mockList);
//        System.out.println("results "+results);
//
//        Assert.assertNotNull(results);
//        Assert.assertEquals(results, mockList);
//
//        Mockito.verify(mockUserRepository, Mockito.times(1)).findAll();
        List<User> users = userService.findAll();
        Mockito.verify(userRepository, Mockito.times(1)).findAll();
        Assert.assertNotNull(users);
    }

    @Test(expected = org.springframework.web.server.ResponseStatusException.class)
    public void findById() {
        Optional<User> optionalUser = Optional.empty();

        Mockito.when(userService.findById(1L)).thenReturn(optionalUser);
        Mockito.verify(userRepository, Mockito.times(1)).findById(1L);

        Assert.assertNotNull(optionalUser);
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
