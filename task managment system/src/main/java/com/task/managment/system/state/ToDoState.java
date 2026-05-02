package com.task.managment.system.state;

import com.task.managment.system.dto.Task;
import com.task.managment.system.enums.TaskStatus;

public class ToDoState implements TaskState{

    @Override
    public void startProgress(Task task) {
        task.setState(new InProgressState());
    }

    @Override
    public void completeTask(Task task) {
        System.out.println("todo directly cannot move to done without in progress");
    }

    @Override
    public void reopenTask(Task task) {
        System.out.println("task already in todo state");
    }

    @Override
    public TaskStatus getStatus() {
        return TaskStatus.TODO;
    }
}
