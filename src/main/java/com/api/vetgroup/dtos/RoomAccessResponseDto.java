package com.api.vetgroup.dtos;

import com.api.vetgroup.models.Room;
import com.api.vetgroup.models.StaffUser;

import java.time.LocalDateTime;

public class RoomAccessResponseDto {

    private Long id;
    private LocalDateTime accessed_at;
    private Room room;
    private StaffReducedDto staff;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getAccessed_at() {
        return accessed_at;
    }

    public void setAccessed_at(LocalDateTime accessed_at) {
        this.accessed_at = accessed_at;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public StaffReducedDto getStaff() {
        return staff;
    }

    public void setStaff(StaffReducedDto staff) {
        this.staff = staff;
    }
}
