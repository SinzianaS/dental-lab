package com.dental.lab.data.domain;

public enum Type {
    METAL_CERAMIC("metal ceramic"),
    VENEER("veneer"),
    ZIRCONIA("zirconia"),
    IMPLANT("implant");

    private final String type;

    Type(String type){
        this.type = type;
    }
}
