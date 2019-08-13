package com.example.sweater.service;

import com.example.sweater.bean.CreateTodoBean;
import com.example.sweater.model.Todo;
import com.example.sweater.model.User;
import com.example.sweater.repository.UserRepository;
import com.example.sweater.service.impl.FileReaderServiceImpl;
import com.example.sweater.service.impl.UserServiceImpl;
import lombok.val;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceUnitTest {

    @Before
    public void before() {
        FileReaderService fileReaderService = new FileReaderServiceImpl();
        fileReaderService.initDatabase();
    }

    @Test
    public void findAll() {
        val mockUserRepository = mock(UserRepository.class);
        val mockTodoService = mock(TodoService.class);
        val mockList = Collections.singletonList(mock(User.class));

        val mockUserService = new UserServiceImpl(mockUserRepository, mockTodoService);

        when(mockUserRepository.findAll())
                .thenReturn(mockList);

        val results = mockUserService.findAll();

        verify(mockUserRepository, times(1)).findAll();

        assertNotNull(results);
        assertEquals(results, mockList);
    }

    @Test
    public void findById() {
        val mockUserRepository = mock(UserRepository.class);
        val mockTodoService = mock(TodoService.class);
        val mockUser = mock(User.class);

        val mockUserService = new UserServiceImpl(mockUserRepository, mockTodoService);

        when(mockUserRepository.findById(anyLong()))
                .thenReturn(Optional.of(mockUser));

        val result = mockUserService.findById(1L);

        verify(mockUserRepository, times(1)).findById(anyLong());
        assertNotNull(result);
        assertEquals(mockUser, result);
    }

    @Test
    public void createUserTodo() {
        val mockUserRepository = mock(UserRepository.class);
        val mockTodoService = mock(TodoService.class);
        val mockTodo = mock(Todo.class);
        val mockCreateTodoBean = mock(CreateTodoBean.class);

        val mockUserService = new UserServiceImpl(mockUserRepository, mockTodoService);

        when(mockTodoService.createTodo(mockCreateTodoBean))
                .thenReturn(mockTodo);

        val result = mockUserService.createUserTodo(anyLong(), mockCreateTodoBean);

        verify(mockTodoService, times(1)).createTodo(mockCreateTodoBean);
        verify(mockCreateTodoBean, times(1)).setUserId(anyLong());

        assertEquals(mockTodo, result);
        assertNotNull(result);
    }

    @Test
    public void updateUserTodo() {
        val mockUserRepository = mock(UserRepository.class);
        val mockTodoService = mock(TodoService.class);
        val mockTodo = mock(Todo.class);
        val mockUser  = mock(User.class);
        val mockCreateTodoBean = mock(CreateTodoBean.class);
        val userId = 1L;

        val mockUserService = new UserServiceImpl(mockUserRepository, mockTodoService);

        when(mockUserRepository.findById(anyLong())) //verify findById(userId);
                .thenReturn(Optional.of(mockUser));

        when(mockTodo.getUserId())  // Objects.equals(foundTodo.getUserId()
                .thenReturn(userId);

        when(mockTodoService.findById(anyLong())) // verify foundTodo = todoService.findById(todoId);
                .thenReturn(mockTodo);

        when(mockTodoService.updateTodo(mockCreateTodoBean))
                .thenReturn(mockTodo);

        val result = mockUserService.updateUserTodo(userId, anyLong(), mockCreateTodoBean);

        verify(mockTodo, times(1)).getUserId();
        verify(mockCreateTodoBean,times(1)).setUserId(anyLong());

        verify(mockUserRepository, times(1)).findById(anyLong());
        verify(mockTodoService, times(1)).updateTodo(mockCreateTodoBean);
        verify(mockCreateTodoBean, times(1)).setUserId(anyLong());

        assertNotNull(result);
        assertEquals(mockTodo, result);
    }

    @Test
    public void deleteById() {
        val mockUserRepository = mock(UserRepository.class);
        val mockTodoService = mock(TodoService.class);
        val mockTodo = mock(Todo.class);
        val mockUser  = mock(User.class);
        val userId = 1L;

        val mockUserService = new UserServiceImpl(mockUserRepository, mockTodoService);

        when(mockUserRepository.findById(anyLong()))
                .thenReturn(Optional.of(mockUser));

        when(mockTodo.getUserId())
                .thenReturn(userId);

        when(mockTodoService.findById(anyLong()))
                .thenReturn(mockTodo);

        when(mockTodoService.deleteById(anyLong()))
                .thenReturn(mockTodo);

        val result = mockUserService.deleteById(userId, anyLong());

        verify(mockTodo, times(1)).getUserId();

        verify(mockUserRepository, times(1)).findById(anyLong());
        verify(mockTodoService, times(1)).deleteById(anyLong());

        assertNotNull(result);
        assertEquals(mockTodo, result);
    }

    @Test
    public void findAllUserBean() {
        val mockUserRepository = mock(UserRepository.class);
        val mockTodoService = mock(TodoService.class);
        val mockList = Collections.singletonList(mock(User.class));
        val mockUserService = new UserServiceImpl(mockUserRepository, mockTodoService);
        val nrOfTodos = 1L;

        when(mockUserRepository.findAll())
                .thenReturn(mockList);

        when(mockTodoService.countTodoForUser(anyLong()))
                .thenReturn(nrOfTodos);

        val result = mockUserService.findAllUserBean();

        verify(mockUserRepository,times(1)).findAll();
        verify(mockTodoService, times(1)).countTodoForUser(anyLong());

        assertNotNull(result);
        assertEquals(result.get(0).getId(), mockList.get(0).getId());
        assertEquals(result.get(0).getUsername(), mockList.get(0).getUsername());
        assertEquals(result.get(0).getNrOfTodos().longValue(), nrOfTodos);
        assertEquals(result.get(0).getCreatedOn(), mockList.get(0).getCreatedOn());
    }
}
