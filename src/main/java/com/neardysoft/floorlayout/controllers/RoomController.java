package com.neardysoft.floorlayout.controllers;

import com.neardysoft.floorlayout.dto.RoomResponse;
import com.neardysoft.floorlayout.models.Point;
import com.neardysoft.floorlayout.services.PointService;
import com.neardysoft.floorlayout.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/api/rooms")
public class RoomController {

    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @PostMapping("/validateRoom")
    public ResponseEntity<?> validateRoom(@RequestBody Point[] points) {

        roomService.validateRoom(points);

        return new ResponseEntity<>(new RoomResponse("Everything ok", points), HttpStatus.OK);
    }

    @GetMapping("/getAllRooms")
    public ResponseEntity<?> getAllRooms() {
        return new ResponseEntity<>(roomService.getAllRooms(), HttpStatus.OK);
    }

}
