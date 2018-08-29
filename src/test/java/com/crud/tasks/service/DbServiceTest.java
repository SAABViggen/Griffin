package com.crud.tasks.service;

import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.mock.mockito.SpyBean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DbServiceTest {

    @InjectMocks
    @SpyBean
    private DbService dbService;

    @Mock
    private TaskRepository repository;

    @Test
    public void shouldFetchAllTasks() {
        // Given
        Task task = new Task(1L, "Task", "Content");
        List<Task> tasks = new ArrayList<>();
        tasks.add(task);
        when(repository.findAll()).thenReturn(tasks);
        // When
        List<Task> result = dbService.getAllTasks();
        // Then
        assertEquals(tasks, result);
    }

    @Test
    public void shouldFindTaskById() {
        // Given
        Task task = new Task(1L, "Task", "Content");
        when(repository.findById(1L)).thenReturn(Optional.of(task));
        // When
        Optional<Task> result = dbService.getTask(1L);
        Optional<Task> expect = Optional.of(task);
        // Then
        assertEquals(expect, result);
    }

    @Test
    public void shouldFindTasksContaining() {
        // Given
        Task task1 = new Task(1L, "Task1", "First");
        Task task2 = new Task(2L, "Task2", "Second");
        List<Task> tasks = new ArrayList<>(Arrays.asList(task1, task2));
        when(repository.findAll()).thenReturn(tasks);
        // When
        List<Task> result = dbService.searchTaskContaining("sec");
        // Then
        assertEquals(1, result.size());
        assertEquals(task2, result.get(0));
    }

    @Test
    public void shouldSaveTask() {
        // Given
        Task task = new Task(1L, "Task", "Content");
        when(repository.save(task)).thenReturn(task);
        // When
        Task result = dbService.saveTask(task);
        // Then
        assertEquals(task, result);
    }

    @Test
    public void shouldDeleteTask() {
        // Given
        Task task = new Task(1L, "Task", "Content");
        List<Task> tasks = new ArrayList<>();
        tasks.add(task);
        doAnswer(invocation -> {
            tasks.remove(0);
            return null;
        }).when(repository).delete(1L);
        // When
        dbService.deleteTask(1L);
        // Then
        assertEquals(0, tasks.size());
    }
}