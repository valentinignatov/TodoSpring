package com.example.sweater.repository.custom;

import com.example.sweater.repository.TodoRepository;
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
public class TodoCustomRepositoryTest {

    @Autowired
    TodoRepository todoCustomRepository;

    @Before
    public void before() {
        FileReaderService fileReaderService = new FileReaderServiceImpl();
        fileReaderService.initDatabase();
    }

    @Test
    public void search() {
        val textToFind = "de gasit";
        val tagName = "not";

        val resultBothParams = todoCustomRepository.search(textToFind, tagName);
        val resultFirstParam = todoCustomRepository.search(textToFind, null);
        val resultSecondParam = todoCustomRepository.search(null, tagName);

        assertNotNull(resultBothParams);
        assertNotNull(resultFirstParam);
        assertNotNull(resultSecondParam);
    }
}