package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TaskControllerTest {

    @InjectMocks
    TaskController taskController;

    @Mock
    DbService service;

    @Mock
    TaskMapper mapper;

    @Test
    public void shouldFetchTasks() {
        // Given
        TaskDto taskDto = new TaskDto(1L, "task", "content");
        List<TaskDto> taskDtos = new ArrayList<>();
        taskDtos.add(taskDto);

        Task task = new Task(1L, "task", "content");
        List<Task> tasks = new ArrayList<>();
        tasks.add(task);

        when(mapper.mapToTaskDtoList(tasks)).thenReturn(taskDtos);
        when(service.getAllTasks()).thenReturn(tasks);
        // When
        List<TaskDto> result = taskController.getTasks();
        // Then
        assertEquals("task", result.get(0).getTitle());
    }

/*    @Test
    public void shouldFetchTaskById() throws TaskNotFoundException {
        // Given
        Task task = new Task(1L, "task", "content");
        TaskDto taskDto = new TaskDto(1L, "task", "content");

        when(mapper.mapToTaskDto(task)).thenReturn(taskDto);
        when(service.getTask(1L).orElseThrow(TaskNotFoundException::new)).thenReturn(task);
        // When
        TaskDto result = taskController.getTask(1L);
        // Then
        assertEquals("task", result.getTitle());
        assertEquals("content", result.getContent());
    }*/
}