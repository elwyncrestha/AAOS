package com.elvin.aaos.core.model.enums;

public enum UserType {

    SUPERADMIN("Super Administrator"),
    ADMIN("Administrator"),
    STUDENT("Student"),
    TEACHER("Teacher"),
    ACADEMIC_STAFF("Academic Staff"),
    OPERATIONAL_STAFF("Operational Staff"),
    ADMISSION_STAFF("Admission Staff");

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
