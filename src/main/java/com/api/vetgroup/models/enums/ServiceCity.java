package com.api.vetgroup.models.enums;

public enum ServiceCity {

    TRINDADE_PE(1),
    OURICURI_PE(2),
    ARARIPINA_PE(3);

    private int code;

    private ServiceCity(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static ServiceCity valueOf(int code) {
        for (ServiceCity value : ServiceCity.values()) {
            if (value.getCode() == code) {
                return value;
            }
        }
        throw new IllegalArgumentException("Invalid Sity code");
    }
}
