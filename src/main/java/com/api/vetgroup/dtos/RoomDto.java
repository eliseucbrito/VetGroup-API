package com.api.vetgroup.dtos;

import com.api.vetgroup.models.enums.RoomType;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;

public class RoomDto {

    @NotBlank
    private String name;
    private Boolean in_use;
    private RoomType type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getIn_use() {
        return in_use;
    }

    public void setIn_use(Boolean in_use) {
        this.in_use = in_use;
    }

    public RoomType getType() {
        return type;
    }

    public void setType(RoomType type) {
        this.type = type;
    }
}
