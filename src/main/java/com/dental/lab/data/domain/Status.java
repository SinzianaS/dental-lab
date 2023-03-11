package com.dental.lab.data.domain;

public enum Status {
    IN_PROGRESS("in progress"),
    COMPLETED("completed");

    private final String status;

    Status(String status) {
        this.status = status;
    }

}
