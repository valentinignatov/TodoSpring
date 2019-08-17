package com.example.sweater.service;

import com.example.sweater.bean.CreateTodoBean;
import com.example.sweater.model.Tag;
import com.example.sweater.model.Todo;
import com.example.sweater.repository.TagRepository;
import com.example.sweater.repository.TodoRepository;
import com.example.sweater.service.impl.FileReaderServiceImpl;
import com.example.sweater.service.impl.TagServiceImpl;
import com.example.sweater.service.impl.TodoServiceImpl;
import lombok.val;
import org.junit.After;
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
public class TodoServiceUnitTest {

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

    @Test
    public void findAll() {
        val mockTodoRepository = mock(TodoRepository.class);
        val mockTagService = mock(TagService.class);
        val mockTodoService = new TodoServiceImpl(mockTodoRepository, mockTagService);
        val mocList = Collections.singletonList(mock(Todo.class));

        when(mockTodoRepository.findAll())
                .thenReturn(mocList);

        val result = mockTodoService.findAll();

        verify(mockTodoRepository, times(1)).findAll();
        assertNotNull(result);
        assertEquals(result, mocList);
    }

    @Test
    public void findById() {
        val mockTodoRepository = mock(TodoRepository.class);
        val mockTagService = mock(TagService.class);
        val mockTodoService = new TodoServiceImpl(mockTodoRepository, mockTagService);
        val mockTodo = mock(Todo.class);

        when(mockTodoRepository.findById(anyLong()))
                .thenReturn(Optional.of(mockTodo));

        val result = mockTodoService.findById(anyLong());

        verify(mockTodoRepository, times(1)).findById(anyLong());
        assertNotNull(result);
        assertEquals(result, mockTodo);
    }

    @Test
    public void save() {
        val mockTodoRepository = mock(TodoRepository.class);
        val mockTagService = mock(TagService.class);
        val mockTodoService = new TodoServiceImpl(mockTodoRepository, mockTagService);
        val mockTodo = mock(Todo.class);

        when(mockTodoRepository.save(mockTodo))
                .thenReturn(mockTodo);

        val result = mockTodoService.save(mockTodo);

        verify(mockTodoRepository, times(1)).save(mockTodo);
        assertNotNull(result);
        assertEquals(result, mockTodo);
    }

    @Test
    public void createTodo() {
        val mockTagRepository = mock(TagRepository.class);
        val mockTodoRepository = mock(TodoRepository.class);
        val mockTagService = spy(new TagServiceImpl(mockTagRepository));
        val mockTodoService = new TodoServiceImpl(mockTodoRepository, mockTagService);
        val mockTodo = mock(Todo.class);
        val mockCreateTodoBean = mock(CreateTodoBean.class);

        doNothing().when(mockTagRepository).addTagForTodo(anyLong(), anyLong());

        when(mockTagService.findById(anyLong()))
                .thenReturn(mock(Tag.class));

        when(mockTodoRepository.findByText(anyString()))
                .thenReturn(Optional.empty());

        when(mockTodoRepository.save(mockTodo))
                .thenReturn(mockTodo);

        doNothing()
                .when(mockTagService)
                .addTagForTodo(anyLong(), anyLong());

        val result = mockTodoService.createTodo(CreateTodoBean.builder().build());

        verify(mockTodoRepository, times(1)).findByText(anyString());
        verify(mockTagService, times(1)).findById(anyLong());
        verify(mockTodoRepository, times(1)).save(mockTodo);
        verify(mockTagService,times(1)).addTagForTodo(anyLong(),1L);

        assertNotNull(result);
        assertEquals(result, mockTodo);
    }

    @Test
    public void updateTodo() {
    }

    @Test
    public void deleteById() {
        val mockTodoRepository = mock(TodoRepository.class);
        val mockTagService = mock(TagService.class);
        val mockTodoService = new TodoServiceImpl(mockTodoRepository, mockTagService);

        mockTodoService.deleteById(anyLong());

        verify(mockTagService, times(1)).deleteByTodoId(anyLong());
        verify(mockTodoRepository, times(1)).deleteById(anyLong());
    }

    @Test
    public void countTodoForUser() {
        val mockTodoRepository = mock(TodoRepository.class);
        val mockTagService = mock(TagService.class);
        val mockTodoService = new TodoServiceImpl(mockTodoRepository, mockTagService);
        Long values = 1L;

        when(mockTodoRepository.countTodoForUser(anyLong()))
                .thenReturn(values);

        val result = mockTodoService.countTodoForUser(anyLong());

        verify(mockTodoRepository, times(1)).countTodoForUser(anyLong());
        assertNotNull(result);
        assertEquals(result, values);
    }

    @Test
    public void findByTextLike() {
        val mockTodoRepository = mock(TodoRepository.class);
        val mockTagService = mock(TagService.class);
        val mockTodoService = new TodoServiceImpl(mockTodoRepository, mockTagService);
        val mockList = Collections.singletonList(mock(Todo.class));

        when(mockTodoRepository.findByTextLike(anyString()))
                .thenReturn(mockList);

        val result = mockTodoService.findByTextLike(anyString());

        verify(mockTodoRepository, times(1)).findByTextLike(anyString());
        assertNotNull(result);
        assertEquals(result, mockList);
    }

    @Test
    public void checkTodoForUser() {
        val mockTodoRepository = mock(TodoRepository.class);
        val mockTagService = mock(TagService.class);
        val mockTodoService = new TodoServiceImpl(mockTodoRepository, mockTagService);
        val mockList = Optional.of(mock(Todo.class));

        when(mockTodoRepository.checkTodoForUser(anyLong(), anyLong()))
                .thenReturn(mockList);

        val result = mockTodoService.checkTodoForUser(anyLong(),anyLong());

        verify(mockTodoRepository, times(1)).checkTodoForUser(anyLong(), anyLong());
        assertNotNull(result);
        assertEquals(result, mockList);
    }

//    @Test
//    public void findByTagLike() {
//        val mockTodoRepository = mock(TodoRepository.class);
//        val mockTagService = mock(TagService.class);
//        val mockTodoService = new TodoServiceImpl(mockTodoRepository, mockTagService);
//        val mockList = Collections.singletonList(mock(Todo.class));
//        Long values = 1L;
//
//        when(mockTagService.findIdByName(anyString()))
//                .thenReturn(values);
//
//        when(mockTodoRepository.findByTagId(anyLong()))
//                .thenReturn(mockList);
//
//        val result = mockTodoService.findByTagLike(anyString());
//
//        verify(mockTagService, times(1)).findIdByName(anyString());
//        verify(mockTodoRepository, times(1)).findByTagId(anyLong());
//        assertNotNull(result);
//        assertEquals(result, mockList);
//    }

    @Test
    public void search() {
        val mockTodoRepository = mock(TodoRepository.class);
        val mockTagService = mock(TagService.class);
        val mockTodoService = new TodoServiceImpl(mockTodoRepository, mockTagService);
        val mockList = Collections.singletonList(mock(Todo.class));

        when(mockTodoRepository.search(anyString(), anyString()))
                .thenReturn(mockList);

        val result = mockTodoService.search(anyString(), anyString());

        verify(mockTodoRepository, times(1)).search(anyString(), anyString());
        assertNotNull(result);
        assertEquals(result, mockList);
    }
}