package com.api.vetgroup.dtos;

import com.api.vetgroup.models.enums.ServiceStatus;
import jakarta.validation.constraints.NotNull;

public class ServiceStatusDto {

    @NotNull
    private ServiceStatus status;

    public ServiceStatus getStatus() {
        return status;
    }

    public void setStatus(ServiceStatus status) {
        this.status = status;
    }
}
