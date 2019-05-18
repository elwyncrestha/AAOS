package com.elvin.aaos.core.model.enums;

public enum RoomType {

    LECTURE_ROOM("Lecture Room"),
    LAB_ROOM("Lab Room");

    private final String value;

    RoomType(String value) { this.value = value; }

    public static RoomType getEnum(String value) {
        if (value == null)
            throw new IllegalArgumentException();
        for (RoomType v : values())
            if (value.equalsIgnoreCase(v.getValue()))
                return v;
        throw new IllegalArgumentException();
    }

    @Override
    public String toString() {
        return value;
    }

    public String getValue() {
        return value;
    }

}
