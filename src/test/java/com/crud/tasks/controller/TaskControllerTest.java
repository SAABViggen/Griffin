package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TaskController.class)
public class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    DbService service;

    @MockBean
    TaskMapper mapper;

    @Test
    public void shouldFetchEmptyList() throws Exception {
        // Given
        List<Task> tasks = new ArrayList<>();
        when(service.getAllTasks()).thenReturn(tasks);
        // When & Then
        mockMvc.perform(get("/v1/task/getTasks").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    public void shouldFetchTasks() throws Exception {
        // Given
        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task(1L, "Task", "Content"));

        when(service.getAllTasks()).thenReturn(tasks);
        // When & Then
        mockMvc.perform(get("/v1/task/getTasks").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1L)))
                .andExpect(jsonPath("$[0].title", is("Task")))
                .andExpect(jsonPath("$[0].content", is("Content")));
    }

    @Test
    public void shouldDeleteTask() throws Exception {
        // Given
        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task(1L, "Task", "Content"));

        when(service.getAllTasks()).thenReturn(tasks);
        // When & Then
        mockMvc.perform(get("/v1/task/getTasks").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));
        mockMvc.perform(delete("/v1/task/deleteTask?taskId=1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        mockMvc.perform(get("/v1/task/getTasks").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    public void shouldUpdateTask() throws Exception {
        // Given
        Task task = new Task(1L, "Task", "Content");

        Gson gson = new Gson();
        String jsonContent = gson.toJson(task);

        when(service.saveTask(task)).thenReturn(task);
        // When & Then
        mockMvc.perform(put("/v1/task/updateTask")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(jsonPath("$.id", is(1L)))
                .andExpect(jsonPath("$.title", is("Task")))
                .andExpect(jsonPath("$.content", is("Content")));
    }

    @Test
    public void shouldCreateTask() throws Exception {
        // Given
        Task task = new Task(1L, "Task", "Content");

        Gson gson = new Gson();
        String jsonContent = gson.toJson(task);

        when(service.getAllTasks()).thenReturn(new ArrayList<>(Arrays.asList(task)));
        // When
        mockMvc.perform(post("/v1/task/createTask")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent));
        // Then
        mockMvc.perform(get("/v1/task/getTasks").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1L)))
                .andExpect(jsonPath("$[0].title", is("Task")))
                .andExpect(jsonPath("$[0].content", is("Content")));
    }
}