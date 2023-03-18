package com.dental.lab.data.domain.enums;

public enum Status {
    IN_PROGRESS("in progress"),
    COMPLETED("completed");

    private final String status;

    Status(String status) {
        this.status = status;
    }


    public String getStatus() {
        return status;
    }


    public static Status getByName(String name) {
        for (Status status : values()) {
            if (status.getStatus().equals(name)) {
                return status;
            }
        }

        throw new IllegalArgumentException(name + " is not a valid Status");
    }


}
