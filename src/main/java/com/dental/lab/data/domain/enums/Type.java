package com.dental.lab.data.domain.enums;

public enum Type {
    METAL_CERAMIC("metal ceramic"),
    VENEER("veneer"),
    ZIRCONIA("zirconia"),
    IMPLANT("implant");

    private final String type;

    Type(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return type;
    }
}
