package com.neardysoft.floorlayout.dto;

import com.neardysoft.floorlayout.models.Point;

public class RoomRequest {

    private Point[] room;

    public RoomRequest() {}

    public RoomRequest(Point[] room) {
        this.room = room;
    }

    public Point[] getRoom() {
        return room;
    }

    public void setRoom(Point[] room) {
        this.room = room;
    }
}
