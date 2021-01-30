package com.taskr.repository;

import com.taskr.domain.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskRepository {
    private List<Task> tasks = new ArrayList<>();
    public  List<Task> findByPriority(){
        return tasks;
    }

    public List<Task> findAll(){
        return tasks;
    }

    public List<Task> findByDate(){
        return tasks;
    }

    public boolean save(){
        return true;
    }

    public boolean delete(){
        return true;
    }

    public Task findById(String id){
        return x;
    }
}
