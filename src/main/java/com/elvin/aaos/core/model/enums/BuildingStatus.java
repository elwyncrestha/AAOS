package com.elvin.aaos.core.model.enums;

public enum BuildingStatus {

    IN_OPERATION("In Operation"), UNDER_CONSTRUCTION("Under Construction"), PROPOSED("Proposed"), DEMOLISHED("Demolished");

    private final String value;

    BuildingStatus(String value) {
        this.value = value;
    }

    public static BuildingStatus getEnum(String value) {
        if (value == null)
            throw new IllegalArgumentException();
        for (BuildingStatus v : values())
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
