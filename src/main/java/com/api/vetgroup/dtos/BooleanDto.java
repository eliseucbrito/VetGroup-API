package com.api.vetgroup.dtos;

import jakarta.validation.constraints.NotNull;

public class BooleanDto {

    @NotNull
    private Boolean on_duty;

    public Boolean getOn_duty() {
        return on_duty;
    }

    public void setOn_duty(Boolean on_duty) {
        this.on_duty = on_duty;
    }
}
