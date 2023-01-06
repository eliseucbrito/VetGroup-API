package com.api.vetgroup.models.enums;

public enum StaffRole {

    CEO(1),
    GENERAL_MANAGER(2),
    MANAGER(3),
    VETERINARY(4),
    ASSISTANT(5),
    INTERN(6);

    private int code;

    private StaffRole(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static StaffRole valueOf(int code) {
        for (StaffRole value : StaffRole.values()) {
            if (value.getCode() == code) {
                return value;
            }
        }
        throw new IllegalArgumentException("Invalid StaffRole code");
    }
}
