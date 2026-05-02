package com.task.managment.system.strategy;

import com.task.managment.system.dto.Task;

import java.util.Comparator;
import java.util.List;

public class DueDateSort implements TaskSortStrategy {
    @Override
    public void sort(List<Task> tasks) {
        tasks.sort(Comparator.comparing(Task::getDueDate));
    }
}
