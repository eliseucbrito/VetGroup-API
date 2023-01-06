package com.api.vetgroup.models.enums;

public enum ReportTypes {

    PAYMENT(1),
    REQUEST(2),
    REPORT(3),
    APPROVED(4),
    REJECTED(5);

    private int code;

    private ReportTypes(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static ReportTypes valueOf(int code) {
        for (ReportTypes value : ReportTypes.values()) {
            if (value.getCode() == code) {
                return value;
            }
        }
        throw new IllegalArgumentException("Invalid ReportType code");
    }
}
