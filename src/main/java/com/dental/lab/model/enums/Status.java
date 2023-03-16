package com.dental.lab.model.enums;


public enum Status {
    IN_PROGRESS("in progress"),
    COMPLETED("completed");

    private final String status;

    Status(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return status;
    }

}
