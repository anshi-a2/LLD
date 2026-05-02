package com.task.managment.system.state;

import com.task.managment.system.dto.Task;
import com.task.managment.system.enums.TaskStatus;

public interface TaskState {
    void startProgress(Task task);
    void completeTask(Task task);
    void reopenTask(Task task);
    TaskStatus getStatus();
}
