package com.example.sweater.controller;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TodoControllerIntegrationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    WebApplicationContext context;

    @Autowired
    private TodoController todoController;

    @Test
    public void findAllTest() throws Exception {
        this.mockMvc.perform(get("/todos/all"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$[0].id", Matchers.notNullValue()))
                .andExpect(jsonPath("$[0].userId", Matchers.notNullValue()))
                .andExpect(jsonPath("$[0].text", Matchers.notNullValue()))
                .andExpect(jsonPath("$[0].createdOn", Matchers.notNullValue()));
    }

    @Test
    public void findByIdTest() throws Exception {
        int id = 2;
        this.mockMvc.perform(get("/todos/{id}", id))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.id", Matchers.notNullValue()))
                .andExpect(jsonPath("$.userId", Matchers.notNullValue()))
                .andExpect(jsonPath("$.text", Matchers.notNullValue()))
                .andExpect(jsonPath("$.createdOn", Matchers.notNullValue()));
    }

    @Test
    public void searchTest() throws Exception {
        String text = "de";
        String tag = "not";

        //request with first param
        this.mockMvc.perform(get("/todos/search")
                    .param("textToFind", text))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$[0].id", Matchers.notNullValue()))
                .andExpect(jsonPath("$[0].userId", Matchers.notNullValue()))
                .andExpect(jsonPath("$[0].text", Matchers.notNullValue()))
                .andExpect(jsonPath("$[0].createdOn", Matchers.notNullValue()));

        //reqquest with second param
        this.mockMvc.perform(get("/todos/search")
                    .param("tagName", tag))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$[0].id", Matchers.notNullValue()))
                .andExpect(jsonPath("$[0].userId", Matchers.notNullValue()))
                .andExpect(jsonPath("$[0].text", Matchers.notNullValue()))
                .andExpect(jsonPath("$[0].createdOn", Matchers.notNullValue()));

        //request with both params
        this.mockMvc.perform(get("/todos/search")
                    .param("textToFind", text)
                    .param("tagName", tag))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$[0].id", Matchers.notNullValue()))
                .andExpect(jsonPath("$[0].userId", Matchers.notNullValue()))
                .andExpect(jsonPath("$[0].text", Matchers.notNullValue()))
                .andExpect(jsonPath("$[0].createdOn", Matchers.notNullValue()));
    }
}

































