package com.virgo.tododone;

/**
 * Created by nazmul on 1/4/18.
 */

public class Task {
    private String taskName;
    private String taskCreatedTime;

    public Task(String taskName, String taskCreatedTime) {
        this.taskName = taskName;
        this.taskCreatedTime = taskCreatedTime;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskCreatedTime() {
        return taskCreatedTime;
    }

    public void setTaskCreatedTime(String taskCreatedTime) {
        this.taskCreatedTime = taskCreatedTime;
    }

}
