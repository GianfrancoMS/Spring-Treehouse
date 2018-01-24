package com.gianfranco.todotoday.service;

import com.gianfranco.todotoday.model.Task;

public interface TaskService {
    Iterable<Task> findAll();

    Task findOne(Long id);

    void toggleComplete(Long id);

    void save(Task task);
}
