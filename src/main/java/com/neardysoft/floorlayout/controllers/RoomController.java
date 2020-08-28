package com.neardysoft.floorlayout.controllers;

import com.neardysoft.floorlayout.dto.RoomResponse;
import com.neardysoft.floorlayout.exceptions.exceptions.NumberNotIntegerException;
import com.neardysoft.floorlayout.models.Point;
import com.neardysoft.floorlayout.services.RoomService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/rooms")
public class RoomController {

    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @PostMapping("/validateRoom")
    public ResponseEntity<?> validateRoom(@RequestBody Point[] points, BindingResult bindingResult) {

        if(bindingResult.hasErrors()) throw new NumberNotIntegerException("Your number is not integer");

        roomService.validateCreatedRoom(points);

        return new ResponseEntity<>(new RoomResponse("Everything ok", points), HttpStatus.OK);
    }

    @GetMapping("/getAllRooms")
    public ResponseEntity<?> getAllRooms() {
        return new ResponseEntity<>(roomService.getAllRooms(), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteRoom(@PathVariable String id) {

        roomService.deleteRoom(id);

        return new ResponseEntity<>("Room deleted successfully", HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateRoom(@RequestBody Point[] points, @PathVariable("id") String id) {

        return roomService.updateRoom(id, points);
    }
}
