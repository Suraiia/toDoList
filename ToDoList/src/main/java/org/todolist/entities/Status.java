package org.todolist.entities;

public enum Status {
    OPEN("Open"),
    STARTED("Started"),
    IN_PROGRESS("In Progress"),
    WAITING("Waiting"),
    BLOCKED("Blocked"),
    FINISHED("Finished");

    private final String displayName;

    Status(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return this.displayName;
    }
}
