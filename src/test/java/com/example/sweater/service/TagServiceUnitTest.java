package com.example.sweater.service;

import com.example.sweater.model.Tag;
import com.example.sweater.repository.TagRepository;
import com.example.sweater.service.impl.FileReaderServiceImpl;
import com.example.sweater.service.impl.TagServiceImpl;
import lombok.val;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TagServiceUnitTest {

    @Before
    public void before() {
        FileReaderService fileReaderService = new FileReaderServiceImpl();
        fileReaderService.initDatabase();
    }

    @Test
    public void findAll() {
        val mockTagRepository = mock(TagRepository.class);
        val mockTagService = new TagServiceImpl(mockTagRepository);
        val mocList = Collections.singletonList(mock(Tag.class));

        when(mockTagRepository.findAll())
                .thenReturn(mocList);

        val result = mockTagService.findAll();

        verify(mockTagRepository, times(1)).findAll();
        assertNotNull(result);
        assertEquals(result, mocList);
    }

    @Test
    public void findById() {
        val id = 1L;
        val mockTagRepository = mock(TagRepository.class);
        val mockTagService = new TagServiceImpl(mockTagRepository);
        val mockTag = mock(Tag.class);

        when(mockTagRepository.findById(id))
                .thenReturn(Optional.of(mockTag));

        val result = mockTagService.findById(id);

        verify(mockTagRepository, times(1)).findById(id);
        assertNotNull(result);
        assertEquals(result, mockTag);
    }

    @Test
    public void addTagForTodo() {
        val mockTagRepository = mock(TagRepository.class);
        val mockTagService = new TagServiceImpl(mockTagRepository);

        mockTagService.addTagForTodo(anyLong(), anyLong());

        verify(mockTagRepository, times(1))
                .addTagForTodo(anyLong(),anyLong());
    }

    @Test
    public void updateTagforTodo() {
        val mockTagRepository = mock(TagRepository.class);
        val mockTagService = new TagServiceImpl(mockTagRepository);

        mockTagService.updateTagforTodo(anyLong(), anyLong());

        verify(mockTagRepository, times(1))
                .updateTagforTodo(anyLong(),anyLong());
    }

    @Test
    public void deleteByTodoId() {
        val mockTagRepository = mock(TagRepository.class);
        val mockTagService = new TagServiceImpl(mockTagRepository);

        mockTagService.deleteByTodoId(anyLong());

        verify(mockTagRepository, times(1))
                .deleteByTodoId(anyLong());
    }
}