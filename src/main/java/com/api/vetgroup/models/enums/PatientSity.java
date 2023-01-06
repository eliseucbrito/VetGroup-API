package com.api.vetgroup.models.enums;

public enum PatientSity {

    TRINDADE(1),
    OURICURI(2),
    ARARIPINA(3);

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
