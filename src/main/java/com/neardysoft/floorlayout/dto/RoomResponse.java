package com.neardysoft.floorlayout.dto;

import com.neardysoft.floorlayout.models.Point;

public class RoomResponse {

    private String message;

    private Point[] points;

    public RoomResponse() {}

    public RoomResponse(String message, Point[] points) {
        this.message = message;
        this.points = points;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Point[] getPoints() {
        return points;
    }

    public void setPoints(Point[] points) {
        this.points = points;
    }
}
