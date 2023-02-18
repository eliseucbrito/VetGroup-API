package com.api.vetgroup.models;

import com.api.vetgroup.models.enums.RoomType;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;

@Entity
@Table(name = "TB_ROOMS")
public class Room implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String name;
    @Column(nullable = false)
    private Integer type;
    @Column(name = "in_use", nullable = false)
    private Boolean inUse;
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "staff_id")
    private StaffUser staff;

    @JsonIgnore
    @OneToMany(mappedBy = "room")
//    @Column(name = "access_list")
    private List<RoomAccessList> access_list = new ArrayList<>();


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
        return inUse;
    }

    public void setIn_use(Boolean inUse) {
        if (inUse == null || !inUse) {
            this.inUse = false;
            this.staff = null;
        } else {
            this.inUse = inUse;
        }
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public StaffUser getStaff() {
        return staff;
    }

    public void setStaff(StaffUser staff) {
        this.staff = staff;
    }

    public List<RoomAccessList> getAccess_list() {
        return access_list;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Room room = (Room) o;

        if (!Objects.equals(id, room.id)) return false;
        if (!Objects.equals(name, room.name)) return false;
        if (!Objects.equals(type, room.type)) return false;
        if (!Objects.equals(inUse, room.inUse)) return false;
        if (!Objects.equals(createdAt, room.createdAt)) return false;
        if (!Objects.equals(staff, room.staff)) return false;
        return Objects.equals(access_list, room.access_list);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (inUse != null ? inUse.hashCode() : 0);
        result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
        result = 31 * result + (staff != null ? staff.hashCode() : 0);
        result = 31 * result + (access_list != null ? access_list.hashCode() : 0);
        return result;
    }
}
