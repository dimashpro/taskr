package com.taskr.repository;

import com.taskr.domain.Task;
import com.taskr.domain.TaskPriority;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class TaskRepository {
    private final List<Task> tasks = new ArrayList<>(Arrays.asList(
            new Task("Meditation", "Meditate at least 30 minutes", Arrays.asList("health", "meditation", "mind"), TaskPriority.MEDIUM, LocalDate.now(), LocalDate.now()),
            new Task("Tekwill Presentation", "Prepare the presenation for webinars", Arrays.asList("work", "teaching", "mind"), TaskPriority.MEDIUM, LocalDate.now(), LocalDate.now()),
            new Task("Practice coding", "Write some coding", Arrays.asList("health", "meditation", "mind"), TaskPriority.MEDIUM, LocalDate.now(), LocalDate.now().plusDays(7))
    ));

    public List<Task> findByPriority(TaskPriority priority) {
        return tasks.stream().filter(t -> t.getPriority() == priority).collect(Collectors.toList());
    }

    public List<Task> findAll() {
        return tasks;
    }

    public List<Task> findByDate(LocalDate date) {
        return tasks.stream().filter(t -> isBetween(date, t.getStartDate(), t.getEndDate())).collect(Collectors.toList());
    }

    private boolean isBetween(LocalDate date, LocalDate startDate, LocalDate endDate) {
        return (date.compareTo(startDate) >= 0) || ((date.compareTo(startDate) >= 0) && (date.compareTo(endDate) >= 0));
    }

    public boolean save(Task task) {
        if (tasks.stream().noneMatch(t -> t.getId().equals(task.getId()))) {
            return tasks.add(task);
        } else {
            Task taskToUpdate = tasks.stream().filter(t -> t.getId().equals(task.getId())).findFirst().get();
            if (task.getTitle() != null && !task.getTitle().isEmpty())
                taskToUpdate.setTitle(task.getTitle());
            if (task.getDescription() != null && !task.getDescription().isEmpty())
                taskToUpdate.setDescription(task.getDescription());
            if (task.getPriority() != null)
                taskToUpdate.setPriority(task.getPriority());
            if (task.getStartDate() != null)
                taskToUpdate.setStartDate(task.getStartDate());
            if (task.getEndDate() != null)
                taskToUpdate.setEndDate(task.getEndDate());
            if (task.getTags() != null && !task.getTags().isEmpty())
                taskToUpdate.setTags(task.getTags());
            return true;
        }
    }

    public boolean delete(Task task) {
        return tasks.remove(task);
    }

    public Task findById(String taskId) {
        return tasks.stream().filter(t -> t.getId().equals(taskId)).findFirst().orElseThrow(NoSuchElementException::new);
    }
}



//
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.List;
//
//public class TaskRepository {
//    private List<Task> tasks = new ArrayList<>();
//    public  List<Task> findByPriority(){
//        return tasks;
//    }
//
//    public List<Task> findAll(){
//        return tasks;
//    }
//
//    public List<Task> findByDate(LocalDate now){
//        return tasks;
//    }
//
//    public boolean save(Task task){
//        return true;
//    }
//
//    public boolean delete(Task task){
//        return true;
//    }
//
//    public Task findById(String id){
//        return x;
//    }
//}
