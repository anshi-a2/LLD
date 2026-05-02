package com.task.managment.system.state;

import com.task.managment.system.dto.Task;
import com.task.managment.system.enums.TaskStatus;

public class DoneState implements TaskState{

    @Override
    public void startProgress(Task task) {
        System.out.println("task already done state cannot move to in progress");
    }

    @Override
    public void completeTask(Task task) {
        System.out.println("task already in done state");
    }

    @Override
    public void reopenTask(Task task) {
        task.setState(new ToDoState());
    }

    @Override
    public TaskStatus getStatus() {
        return TaskStatus.INPROGRESS;
    }
}
