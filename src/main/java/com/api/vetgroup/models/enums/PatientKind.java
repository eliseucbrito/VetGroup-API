package com.api.vetgroup.models.enums;

public enum PatientKind {

    CAT(1),
    DOG(2),
    TURTLE(3);

    private int code;

    private PatientKind(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static PatientKind valueOf(int code) {
        for (PatientKind value : PatientKind.values()) {
            if (value.getCode() == code) {
                return value;
            }
        }
        throw new IllegalArgumentException("Invalid PatientKind code");
    }
}
