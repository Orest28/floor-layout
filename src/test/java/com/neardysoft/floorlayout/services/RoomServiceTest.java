package com.neardysoft.floorlayout.services;

import com.neardysoft.floorlayout.exceptions.exceptions.NumberOfAnglesException;
import com.neardysoft.floorlayout.exceptions.exceptions.RoomInfiniteException;
import com.neardysoft.floorlayout.exceptions.exceptions.WallsDiagonalException;
import com.neardysoft.floorlayout.exceptions.exceptions.WallsIntersectException;
import com.neardysoft.floorlayout.models.Point;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class RoomServiceTest {

    @InjectMocks
    private RoomService roomService;

    private final Point[] errorNumberPoints = new Point[] {new Point(1,1), new Point(2,2), new Point(2,1)};

    private final Point[] errorDiagonalWallsPoints = new Point[] {new Point(1,1), new Point(2,3), new Point(2,-1), new Point(1,-1)};

    private final Point[] errorWallsIntersectPoints = new Point[] {new Point(1,1), new Point(3,1), new Point(3,-1), new Point(2,-1), new Point(2,2), new Point(1,2)};

    private final Point[] errorInfinitePoints = new Point[] {new Point(1,1),new Point(1,2),new Point(0,2),new Point(0,3)};

    @Test
    public void testLowNumberOfCornersException() {
        assertThrows(NumberOfAnglesException.class, () -> roomService.validateRoom(errorNumberPoints));
    }

    @Test
    public void testDiagonalWallsException() {
        assertThrows(WallsDiagonalException.class, () -> roomService.validateRoom(errorDiagonalWallsPoints));
    }

    @Test
    public void testWallsIntersectException() {
        assertThrows(WallsIntersectException.class, () -> roomService.validateRoom(errorWallsIntersectPoints));
    }

    @Test
    public void testRoomInfiniteException() {
        assertThrows(RoomInfiniteException.class, () -> roomService.validateRoom(errorInfinitePoints));
    }
}