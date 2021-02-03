package com.taskr.service;

import com.taskr.domain.Task;
import com.taskr.domain.TaskPriority;
import com.taskr.repository.TaskRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class TaskService {
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> getByPriority(TaskPriority taskPriority){
        return taskRepository.findByPriority(taskPriority);
    }

    public List<Task> getAll(){
        return taskRepository.findAll();
    }
    public List<Task> getForToday(){
        return taskRepository.findByDate(LocalDate.now());
    }

    public boolean save(Task task){
        return taskRepository.save(task);
    }

    public boolean update(Task task) {
        return taskRepository.save(task);
    }

    public boolean delete(Task task){
        return taskRepository.delete(task);
    }

    public Task getById(String id){
        return taskRepository.findById(id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaskService that = (TaskService) o;
        return Objects.equals(taskRepository, that.taskRepository);
    }

    @Override
    public int hashCode() {
        return Objects.hash(taskRepository);
    }

    @Override
    public String toString() {
        return "TaskService{" +
                "taskRepository=" + taskRepository +
                '}';
    }
}
