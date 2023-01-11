package com.api.vetgroup.models.enums;

public enum RoomType {

    CLINIC(1),
    EXAM(2),
    EMERGENCY(3),
    LABORATORY(4);

    private int code;

    private RoomType(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static RoomType valueOf(int code) {
        for (RoomType value : RoomType.values()) {
            if (value.getCode() == code) {
                return value;
            }
        }
        throw new IllegalArgumentException("Invalid RoomType code");
    }
}
