package com.api.vetgroup.controllers;

import com.api.vetgroup.models.Room;
import com.api.vetgroup.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(value = "/rooms")
public class RoomController {

    @Autowired
    private RoomService service;

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
}
