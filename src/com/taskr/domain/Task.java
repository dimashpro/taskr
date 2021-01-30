package com.taskr.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Task {

    public String id;
    public String title;
    public String description;
    List<String> tags = new ArrayList<>();
    TaskPriority priority;
    LocalDate startDate, endDate;

    public Task(String id, String title, String description, List<String> tags, TaskPriority priority, LocalDate startDate, LocalDate endDate) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.tags = tags;
        this.priority = priority;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(id, task.id) &&
                Objects.equals(title, task.title) &&
                Objects.equals(description, task.description) &&
                Objects.equals(tags, task.tags) &&
                priority == task.priority &&
                Objects.equals(startDate, task.startDate) &&
                Objects.equals(endDate, task.endDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, tags, priority, startDate, endDate);
    }

    @Override
    public String toString() {
        return "Task{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", tags=" + tags +
                ", priority=" + priority +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}
