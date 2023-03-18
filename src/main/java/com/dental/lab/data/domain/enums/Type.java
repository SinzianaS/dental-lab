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

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return type;
    }

    public static Type getByName(String name) {
        for (Type type : values()) {
            if (type.getType().equals(name)) {
                return type;
            }
        }

        throw new IllegalArgumentException(name + " is not a valid Status");
    }
}
