package com.neardysoft.floorlayout.services;

import com.neardysoft.floorlayout.exceptions.exceptions.NumberOfAnglesException;
import com.neardysoft.floorlayout.exceptions.exceptions.RoomInfiniteException;
import com.neardysoft.floorlayout.exceptions.exceptions.WallsDiagonalException;
import com.neardysoft.floorlayout.exceptions.exceptions.WallsIntersectException;
import com.neardysoft.floorlayout.models.Point;
import com.neardysoft.floorlayout.models.Room;
import com.neardysoft.floorlayout.repositories.PointRepository;
import com.neardysoft.floorlayout.repositories.RoomRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.stream.Collectors;

@Service
public class RoomService {

    private final RoomRepository roomRepository;

    private final PointRepository pointRepository;

    public RoomService(RoomRepository roomRepository, PointRepository pointRepository) {
        this.roomRepository = roomRepository;
        this.pointRepository = pointRepository;
    }


    public Iterable<Room> getAllRooms() {
        return roomRepository.findAll();
    }


    public void validateRoom(Point[] points) {

        if(points.length < 4) throw new NumberOfAnglesException("Illegal, Your set of points form only two corners");
        checkDiagonalWalls(points);
        checkInfiniteRoom(points);
        checkIntersectRoom(points);

        Room room = new Room("default", Arrays.stream(points).collect(Collectors.toList()));

        for(Point point : points) {
            point.setRoom(room);
        }

        pointRepository.saveAll(Arrays.stream(points).collect(Collectors.toList()));
    }

    public void checkDiagonalWalls(Point[] points) {

        // check all coordinates, except from last to first

        for(int i = 0; i < points.length - 1; i++) {
            if(points[i].getX() != points[i+1].getX() && points[i].getY() != points[i+1].getY()) {
                throw new WallsDiagonalException("Illegal data, as it would result in the wall going diagonally");
            }
        }

        // check from last to first coordinate

        if((points[points.length - 1].getY() != points[0].getY() && points[points.length - 1].getX() != points[0].getX())) throw new WallsDiagonalException("Illegal data, as it would result in the wall going diagonally");
    }

    public void checkInfiniteRoom(Point[] points) {

        int determinant = 0;

        for(int i = 0; i < points.length - 1; i++) {
            determinant += (points[i+1].getX() - points[i].getX()) * (points[i+1].getY() + points[i+1].getY());
        }

        determinant += (points[0].getX() - points[points.length - 1].getX()) * (points[0].getY() + points[points.length - 1].getY());

        if(determinant < 0) throw new RoomInfiniteException("Illegal, your set of coordinates form infinite area");
    }

    public void checkIntersectRoom(Point[] points) {

        if(points.length != Arrays.stream(points).collect(Collectors.toSet()).size()) throw new WallsIntersectException("Illegal, walls intersect");

        for(int i = 0; i < points.length - 1; i++) {
            for(int j = 0; j < points.length - 1; j++) {

                if(i == j) continue;

                if(checkIntersectionTwoLines(points[i], points[i+1], points[j], points[j+1])) throw new WallsIntersectException("Illegal, walls intersect");
            }
        }

        for(int i = 0; i < points.length - 1; i++) {
            if(checkIntersectionTwoLines(points[points.length - 1], points[0], points[i], points[i+1])) throw new WallsIntersectException("Illegal, walls intersect");
        }
    }

    public boolean checkIntersectionTwoLines(Point p1, Point p2, Point p3, Point p4) {
        double v1 = (p4.getX() - p3.getX()) * (p1.getY() - p3.getY()) - (p4.getY() - p3.getY()) * (p1.getX() - p3.getX());
        double v2 = (p4.getX() - p3.getX()) * (p2.getY() - p3.getY()) - (p4.getY() - p3.getY()) * (p2.getX() - p3.getX());
        double v3 = (p2.getX() - p1.getX()) * (p3.getY() - p1.getY()) - (p2.getY() - p1.getY()) * (p3.getX() - p1.getX());
        double v4 = (p2.getX() - p1.getX()) * (p4.getY() - p1.getY()) - (p2.getY() - p1.getY()) * (p4.getX() - p1.getX());

        return (v1*v2 < 0) && (v3 * v4 < 0);
    }
}
