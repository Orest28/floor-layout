package com.neardysoft.floorlayout.controllers;


import com.neardysoft.floorlayout.services.PointService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class PageController {

    private final PointService pointService;

    public PageController(PointService pointService) {
        this.pointService = pointService;
    }

    @GetMapping("")
    public String home() {
        return "Index";
    }

    @GetMapping("/rooms")
    public String rooms() { return "Rooms"; }

    @GetMapping("/room/{roomId}")
    public String room(@PathVariable String roomId, Model model) {

        model.addAttribute("points", pointService.getPointsFromRoom(roomId));

        return "Room";
    }
}
