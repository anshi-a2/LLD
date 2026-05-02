package com.task.managment.system.strategy;

import com.task.managment.system.dto.Task;

import java.util.Comparator;
import java.util.List;

public class PrioritySort implements TaskSortStrategy {
    @Override
    public void sort(List<Task> tasks) {
        // Higher priority (lower enum ordinal) comes first
        tasks.sort(Comparator.comparing(Task::getPriority).reversed());
    }
}
