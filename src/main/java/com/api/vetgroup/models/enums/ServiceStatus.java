package com.api.vetgroup.models.enums;

public enum ServiceStatus {

    SCHEDULED(1),
    NOT_INITIALIZED(2),
    IN_PROGRESS(3),
    COMPLETED(4),
    WAITING_PAYMENT(5),
    PAID(6),
    CANCELED(7);

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
