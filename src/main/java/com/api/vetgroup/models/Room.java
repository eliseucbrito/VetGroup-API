package com.api.vetgroup.models;

import com.api.vetgroup.models.enums.RoomType;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "TB_ROOMS")
public class Room implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String name;
    @Column(nullable = false, unique = false)
    private Integer type;
    @Column(nullable = false, unique = false)
    private Boolean in_use;


    private StaffUser staff;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RoomType getType() {
        return RoomType.valueOf(type);
    }

    public void setType(RoomType type) {
        if (type != null) {
            this.type = type.getCode();
        }
    }

    public Boolean getIn_use() {
        return in_use;
    }

    public void setIn_use(Boolean in_use) {
        if (in_use == null) {
            this.in_use = false;
        } else {
            this.in_use = in_use;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Room room = (Room) o;

        return Objects.equals(id, room.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
