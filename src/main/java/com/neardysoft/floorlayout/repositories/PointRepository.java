package com.neardysoft.floorlayout.repositories;

import com.neardysoft.floorlayout.models.Point;
import org.springframework.data.repository.CrudRepository;

public interface PointRepository extends CrudRepository<Point, Long> {

    Iterable<Point> getAllByRoomId(Long room_id);
}
