package com.task.managment.system.dto;

import com.task.managment.system.enums.TaskPriority;
import com.task.managment.system.enums.TaskStatus;
import com.task.managment.system.state.TaskState;
import com.task.managment.system.state.ToDoState;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Task {
    // Getters and setters
    @Getter
    private final String id;
    @Setter
    @Getter
    private String title;
    private String desc;
    @Getter
    private LocalDate dueDate;
    @Getter
    private TaskPriority priority;
    private final User createdBy;
    @Getter
    @Setter
    private User assignee;
    private TaskState currentState;
    private final List<Comment> comments;
    private final List<Task> subtasks;

    private Task(TaskBuilder builder) {
        this.id = builder.id;
        this.title = builder.title;
        this.desc = builder.desc;
        this.dueDate = builder.dueDate;
        this.priority = builder.priority;
        this.createdBy = builder.createdBy;
        this.assignee = builder.assignee;
        this.currentState = new ToDoState(); // Initial state
        this.comments = new ArrayList<>();
        this.subtasks = new ArrayList<>();
    }

    public void setState(TaskState state) {
        this.currentState = state;
    }
    public void startProgress() { currentState.startProgress(this); }
    public void completeTask() { currentState.completeTask(this); }
    public void reopenTask() { currentState.reopenTask(this); }
    public TaskStatus getStatus() {return currentState.getStatus();}

    public static class TaskBuilder {
        private final String id;
        private String title;
        private String desc = "";
        private LocalDate dueDate;
        private TaskPriority priority;
        private User createdBy;
        private User assignee;

        public TaskBuilder(String title) {
            this.id = UUID.randomUUID().toString();
            this.title = title;
        }

        public TaskBuilder description(String description) { this.desc = description; return this; }
        public TaskBuilder dueDate(LocalDate dueDate) { this.dueDate = dueDate; return this; }
        public TaskBuilder priority(TaskPriority priority) { this.priority = priority; return this; }
        public TaskBuilder assignee(User assignee) { this.assignee = assignee; return this; }
        public TaskBuilder createdBy(User createdBy) { this.createdBy = createdBy; return this; }

        public Task build() {
            return new Task(this);
        }
    }


    public synchronized void updatePriority(TaskPriority priority) {
        this.priority = priority;
    }

    public synchronized void addComment(Comment comment) {
        comments.add(comment);
    }

    public synchronized void addSubtask(Task subtask) {
        subtasks.add(subtask);
    }

    public String getDescription() {
        return desc;
    }

    public void setDescription(String description) {
        this.desc = description;
    }


}
