package com.elvin.aaos.core.model.enums;

public enum MessageType {

    SUCCESS("Success"), ERROR("Error");

    private final String value;

    MessageType(String value) { this.value = value; }

    public static MessageType getEnum(String value) {
        if (value == null)
            throw new IllegalArgumentException();
        for (MessageType v : values())
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
