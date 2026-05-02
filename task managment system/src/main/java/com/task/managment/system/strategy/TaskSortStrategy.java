package com.task.managment.system.strategy;

import com.task.managment.system.dto.Task;

import java.util.List;

public interface TaskSortStrategy {

    void sort(List<Task> tasks);
}
