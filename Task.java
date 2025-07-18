class Task {
    int taskNumber;
    int creationTime;
    int executionTime;
    int priority;

    Task(int creationTime, int executionTime, int priority, int TaskNumber) {
        this.creationTime = creationTime;
        this.executionTime = executionTime;
        this.priority = priority;
        this.taskNumber = TaskNumber;
    }

    public int getTaskNumber() {
        return taskNumber;
    }

    public int getExecutionTime() {
        return executionTime;
    }

    public int getPriority() {
        return priority;
    }
}