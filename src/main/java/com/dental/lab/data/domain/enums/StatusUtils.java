package com.dental.lab.data.domain.enums;

public class StatusUtils {

    public static Status getByName(String name) {
        return Status.valueOf(name);
    }

    public static Status getByUpperCaseName(String name) {
        if (name == null || name.isEmpty()) {
            return null;
        }

        return Status.valueOf(name.toUpperCase());
    }

    public static void main(String[] args) {
        System.out.println(getByUpperCaseName("completed"));
        System.out.println(getByUpperCaseName("in_progress"));
    }
}
