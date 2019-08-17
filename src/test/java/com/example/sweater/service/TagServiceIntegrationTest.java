package com.example.sweater.service;

import com.example.sweater.repository.TagRepository;
import com.example.sweater.repository.TodoRepository;
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
public class TagServiceIntegrationTest {

    @Before
    public void before() {
        FileReaderService fileReaderService = new FileReaderServiceImpl();
        fileReaderService.initDatabase();
    }

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private TagService tagService;

    @Autowired
    private TodoRepository todoRepository;

    @Test
    public void findAll() {
        val id = 1L;
        val name = "not important";

        val tags = tagService.findAll();

        assertNotNull(tags);
        assertEquals(tags.get(0).getId().longValue(), id);
        assertEquals(tags.get(0).getName(), name);
    }

    @Test
    public void findById() {
        val id = 1L;
        val name = "not important";
        val tag = tagService.findById(id);

        assertNotNull(tag);
        assertEquals(tag.getId().longValue(), id);
        assertEquals(tag.getName(), name);
    }

    @Test
    public void addTagForTodo() {
        val tagId = 1L;
        val todoId = 1L;
        val countBefore = todoRepository.countTodosToTags();

        tagService.addTagForTodo(tagId, todoId);

        val countAfter = todoRepository.countTodosToTags();

        assertTrue(countAfter > countBefore);
    }

    @Test
    public void updateTagforTodo() {
        val tagId = 2L;
        val todoId = 1L;

        val tagBefore = todoRepository.verifyTagIdByTodoId(todoId);

        tagService.updateTagforTodo(tagId, todoId);

        val tagAfter = todoRepository.verifyTagIdByTodoId(todoId);

        assertTrue(tagAfter.get(0) == tagId);
        assertNotEquals(tagAfter, tagBefore);
    }

    @Test
    public void deleteByTodoId() {
        val todoId = 1L;

        val countBefore = todoRepository.countTodosToTags();

        tagRepository.deleteByTodoId(todoId);

        val countAfter = todoRepository.countTodosToTags();

        assertTrue(countAfter < countBefore);
    }
}