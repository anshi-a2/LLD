package com.task.managment.system.state;

import com.task.managment.system.dto.Task;
import com.task.managment.system.enums.TaskStatus;

public class InProgressState implements TaskState{

    @Override
    public void startProgress(Task task) {
        System.out.println("task already in inprogress state");
    }

    @Override
    public void completeTask(Task task) {
        task.setState(new DoneState());
    }

    @Override
    public void reopenTask(Task task) {
        System.out.println("task already in inprogress state cannot repoen");
    }

    @Override
    public TaskStatus getStatus() {
        return TaskStatus.INPROGRESS;
    }
}
