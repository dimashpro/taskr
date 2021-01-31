package com.taskr.service;

import com.taskr.domain.Task;
import com.taskr.domain.TaskPriority;
import com.taskr.repository.TaskRepository;

import java.time.LocalDate;
import java.util.List;

public class TaskService {
    private final TaskRepository taskRepository = new TaskRepository();
    public List<Task> getByPriority(TaskPriority taskPriority){

    }

    public List<Task> getAll(){

    }
    public void getForToday(LocalDate localDate){

    }

    public boolean save(Task task){

    }

    public boolean delete(Task task){

    }

    public String getById(String id){
        
    }
}
