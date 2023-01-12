package com.api.vetgroup.services;

import com.api.vetgroup.models.Room;
import com.api.vetgroup.models.RoomAccessList;
import com.api.vetgroup.models.StaffUser;
import com.api.vetgroup.repositories.RoomAccessRepository;
import com.api.vetgroup.repositories.RoomRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

@Service
public class RoomService {

    @Autowired
    private RoomRepository repository;

    @Autowired
    private RoomAccessRepository roomAccessRepository;

    @Autowired
    private StaffUserService staffService;

    public List<Room> findAll() {
        return repository.findAll();
    }

    public List<RoomAccessList> findAllRoomAccess() {
        return roomAccessRepository.findAll();
    }

    public Room findById(Long id) {
        Optional<Room> obj =  repository.findById(id);
        return obj.get();
    }

    @Transactional
    public Room insert(Room newRoom) {
        return repository.save(newRoom);
    }

    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Transactional
    public void update(Room room) {
        repository.save(room);
    }

    public void changeInUse(Long id, Boolean in_use, Long staff_id) {

        Room room = findById(id);

        if (in_use && room.getIn_use()) {
            throw new IllegalArgumentException("Room is already in use by "+room.getStaff().getFull_name());
        }

        if (in_use) {
            StaffUser staff = staffService.findById(staff_id);
            RoomAccessList roomAccess = new RoomAccessList();
            roomAccess.setRoom(room);
            roomAccess.setStaff(staff);
            roomAccess.setAccessed_at(LocalDateTime.now(ZoneId.of("UTC")));
            roomAccessRepository.save(roomAccess);

            room.setIn_use(true);
            room.setStaff(staff);
        }

        if (!in_use) {
            room.setIn_use(false);
            room.setStaff(null);
        }

        update(room);
    }
}
