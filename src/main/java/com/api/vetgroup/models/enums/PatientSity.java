package com.api.vetgroup.models.enums;

public enum PatientSity {

    TRINDADE_PE(1),
    OURICURI_PE(2),
    ARARIPINA_PE(3);

    private int code;

    private PatientSity(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static PatientSity valueOf(int code) {
        for (PatientSity value : PatientSity.values()) {
            if (value.getCode() == code) {
                return value;
            }
        }
        throw new IllegalArgumentException("Invalid Sity code");
    }
}
