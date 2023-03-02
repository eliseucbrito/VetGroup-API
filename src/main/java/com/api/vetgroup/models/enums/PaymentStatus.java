package com.api.vetgroup.models.enums;

public enum PaymentStatus {

    WAITING_PAYMENT(1),
    PAID(2),
    CANCELED(3);

    private int code;

    private PaymentStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static PaymentStatus valueOf(int code) {
        for (PaymentStatus value : PaymentStatus.values()) {
            if (value.getCode() == code) {
                return value;
            }
        }
        throw new IllegalArgumentException("Invalid PaymentStatus code");
    }
}
