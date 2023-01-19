package com.api.vetgroup.controllers;

import com.api.vetgroup.dtos.RoomDto;
import com.api.vetgroup.models.Room;
import com.api.vetgroup.models.RoomAccessList;
import com.api.vetgroup.services.RoomService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(value = "/api/rooms/v1")
public class RoomController {

    @Autowired
    private RoomService service;

    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> createNewRoom(@RequestBody @Valid RoomDto roomDto) {
        var roomModel = new Room();
        BeanUtils.copyProperties(roomDto, roomModel);
        roomModel.setType(roomDto.getType());
        roomModel.setCreated_at(LocalDateTime.now(ZoneId.of("UTC")));
        return ResponseEntity.status(HttpStatus.CREATED).body(service.insert(roomModel));
    }

    @PatchMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> updateInUse(
            @PathVariable Long id,
            @RequestBody RoomDto roomDto)
    {

        try {
            service.changeInUse(id, roomDto.getIn_use(), roomDto.getStaff_id());
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }


    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Room>> findAll() {
        List<Room> list = service.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @GetMapping(value = "/access-list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<RoomAccessList>> findAllRoomAccess() {
        List<RoomAccessList> list = service.findAllRoomAccess();
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Room> findById(@PathVariable Long id) {
        Room obj = service.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(obj);
    }

    @DeleteMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deleteRoom(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
