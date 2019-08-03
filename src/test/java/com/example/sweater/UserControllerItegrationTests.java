package com.example.sweater;

import com.example.sweater.bean.CreateTodoBean;
import com.example.sweater.controller.UserController;
import com.example.sweater.service.TodoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.context.WebApplicationContext;

import java.util.Random;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerItegrationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    WebApplicationContext context;

    @Autowired
    private UserController controller;

    @Autowired
    TodoService todoService;

    @Test
    public void findAllTest() throws Exception {
        this.mockMvc.perform(get("/users/findAll"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$[0].id", Matchers.notNullValue()))
                .andExpect(jsonPath("$[0].username", Matchers.notNullValue()))
                .andExpect(jsonPath("$[0].nrOfTodos", Matchers.notNullValue()))
                .andExpect(jsonPath("$[0].createdOn", Matchers.notNullValue()));
    }

    @Test
    public void findByIdTest() throws Exception {
        int id = 1;
        this.mockMvc.perform(get("/users/{id}", id))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.id", Matchers.notNullValue()))
                .andExpect(jsonPath("$.username", Matchers.notNullValue()))
                .andExpect(jsonPath("$.createdOn", Matchers.notNullValue()));
    }

    @Test
    public void createTodoTest() throws Exception {
        CreateTodoBean createTodoBean = mockbean(1L, 1L);
        byte[] createTodoBeanJson = toJson(createTodoBean);

        //Create request
        MvcResult resultCreate = mockMvc.perform(post("/users/{userId}/todos/add",
                                                createTodoBean.getUserId())
                .content(createTodoBeanJson)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", Matchers.notNullValue()))
                .andExpect(jsonPath("$.userId", Matchers.notNullValue()))
                .andExpect(jsonPath("$.text", Matchers.notNullValue()))
                .andReturn();

        resultCreate.getResponse();

        Long id = todoService.search("_Making TEST", "not").get(0).getId() ;

        //Update request

        CreateTodoBean updateTodo = mockbean(1L, 1L);
        byte[] updateTodoJson = toJson(updateTodo);

        MvcResult resultUpdate = mockMvc.perform(put("/users/{userId}/todos/{todoId}/update",
                createTodoBean.getUserId(), id)
                .content(updateTodoJson)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.userId", Matchers.notNullValue()))
                .andExpect(jsonPath("$.text", Matchers.notNullValue()))
                .andExpect(status().isOk())
                .andReturn();

        resultUpdate.getResponse();

        System.out.println(">>>>>>>>>>>>>>>>Update" + resultUpdate.getResponse());


        //Delete request
        MvcResult resultDelete = mockMvc.perform(delete("/users/{userId}/todos/{todoId}/delete",
                createTodoBean.getUserId(), id)
                .content(updateTodoJson)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        resultDelete.getResponse();
    }

    private CreateTodoBean mockbean(Long userId, Long tagId) {
        CreateTodoBean bean = new CreateTodoBean();
        bean.setUserId(userId);
        bean.setTagId(tagId);
        bean.setText(new Random().toString() + "_Making TEST");
        return bean;
    }

    private byte[] toJson(Object object) throws Exception {
        ObjectMapper map = new ObjectMapper();
        return map.writeValueAsString(object).getBytes();
    }

    public long getResourceIdFromUrl(String locationUrl) {
        String[] parts = locationUrl.split(",");
        return Long.valueOf(parts[parts.length - 1]);
    }

}