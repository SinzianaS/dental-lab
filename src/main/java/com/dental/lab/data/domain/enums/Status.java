package com.dental.lab.data.domain.enums;

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
