package com.api.vetgroup.services;

import com.api.vetgroup.models.Room;
import com.api.vetgroup.repositories.RoomRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoomService {

    @Autowired
    private RoomRepository repository;

    public List<Room> findAll() {
        return repository.findAll();
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
}
