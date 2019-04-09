package com.elvin.aaos.core.model.enums;

public enum UserType {

    SUPER_ADMIN("SuperAdmin"),
    ADMIN("Admin");

    private final String value;

    UserType(String value) {
        this.value = value;
    }

    public static UserType getEnum(String value) {
        if (value == null)
            throw new IllegalArgumentException();
        for (UserType v : values())
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
