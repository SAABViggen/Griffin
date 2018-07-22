package com.crud.tasks.service;

import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        List<Task> tasks = getAllTasks();
        List<Task> result = new ArrayList<>();
        for (Task t : tasks) {
            if (t.getContent().toLowerCase().contains(str.toLowerCase()) || t.getTitle().toLowerCase().contains(str.toLowerCase())) {
                result.add(t);
            }
        }
        return result;
    }

    public Task saveTask(final Task task) {
        return repository.save(task);
    }

    public void deleteTask(final Long id) {
        repository.delete(id);
    }
}