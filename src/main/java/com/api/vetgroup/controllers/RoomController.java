package com.api.vetgroup.controllers;

import com.api.vetgroup.dtos.RoomDto;
import com.api.vetgroup.models.Room;
import com.api.vetgroup.services.RoomService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(value = "/rooms")
public class RoomController {

    @Autowired
    private RoomService service;

    @PostMapping(value = "/create")
    public ResponseEntity<Object> createNewRoom(@RequestBody @Valid RoomDto roomDto) {
        var roomModel = new Room();
        BeanUtils.copyProperties(roomDto, roomModel);
        roomModel.setType(roomDto.getType());
        roomModel.setCreated_at(LocalDateTime.now(ZoneId.of("UTC")));
        return ResponseEntity.status(HttpStatus.CREATED).body(service.insert(roomModel));
    }

    @GetMapping
    public ResponseEntity<List<Room>> findAll() {
        List<Room> list = service.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Room> findById(@PathVariable Long id) {
        Room obj = service.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(obj);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteRoom(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
