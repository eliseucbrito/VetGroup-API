package com.api.vetgroup.models.enums;

public enum ServiceStatus {

    SCHEDULED(1),
    WAITING_PAYMENT(2),
    PAID(3),
    CANCELED(4),
    NOT_INITIALIZED(5),
    IN_PROGRESS(6),
    COMPLETED(7);


    private int code;

    private ServiceStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static ServiceStatus valueOf(int code) {
        for (ServiceStatus value : ServiceStatus.values()) {
            if (value.getCode() == code) {
                return value;
            }
        }
        throw new IllegalArgumentException("Invalid StaffRole code");
    }
}
