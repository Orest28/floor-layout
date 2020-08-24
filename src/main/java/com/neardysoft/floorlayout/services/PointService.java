package com.neardysoft.floorlayout.services;

import com.neardysoft.floorlayout.models.Point;
import com.neardysoft.floorlayout.repositories.PointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PointService {

    private final PointRepository pointRepository;

    public PointService(PointRepository pointRepository) {
        this.pointRepository = pointRepository;
    }


    public Iterable<Point> getPointsFromRoom(String roomId) {
        return pointRepository.getAllByRoomId(Long.parseLong(roomId));
    }
}
