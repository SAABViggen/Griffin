package com.crud.tasks.service;

import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DbService {

    @Autowired
    private TaskRepository repository;

    public List<Task> getAllTasks() {
        return repository.findAll();
    }

    public Optional<Task> getTask(final Long id) {
        return repository.findById(id);
    }

    public List<Task> searchTaskContaining(final String str) {
        return getAllTasks().stream()
                .filter(t -> t.getContent().toLowerCase().contains(str.toLowerCase())
                        || t.getTitle().toLowerCase().contains(str.toLowerCase()))
                .collect(Collectors.toList());
    }

    public Task saveTask(final Task task) {
        return repository.save(task);
    }

    public void deleteTask(final Long id) {
        repository.delete(id);
    }
}