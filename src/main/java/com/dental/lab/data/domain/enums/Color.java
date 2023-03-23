package com.dental.lab.data.domain.enums;

public enum Color {
    A1("a1"),
    A2("a2"),
    A3("a3"),
    A35("a3.5"),
    A4("a4"),
    B1("b1"),
    B2("b2"),
    B3("b3"),
    C1("c1"),
    C2("c2"),
    C3("c3"),
    D2("d2"),
    D3("d3"),
    BL1("bl1"),
    BL2("bl2"),
    BL3("bl3"),
    BL4("bl4");

    private String color;

    Color(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return color;
    }

    public String getColor() {
        return color;
    }

    public static Color getByName(String name) {
        for (Color color : values()) {
            if (color.getColor().equals(name)) {
                return color;
            }
        }

        throw new IllegalArgumentException(name + " is not a valid Status");
    }
}
