package com.api.vetgroup.models.enums;

public enum ServiceTypes {

    EXAM(1),
    MEDICAL_CARE(2),
    HOME_CARE(3),
    SURGERY(4),
    EMERGENCY(5);

    private int code;

    private ServiceTypes(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static ServiceTypes valueOf(int code) {
        for (ServiceTypes value : ServiceTypes.values()) {
            if (value.getCode() == code) {
                return value;
            }
        }
        throw new IllegalArgumentException("Invalid PatientKind code");
    }
}
