package com.neardysoft.floorlayout.exceptions.handler;

import com.neardysoft.floorlayout.dto.ErrorResponse;
import com.neardysoft.floorlayout.exceptions.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(RoomInfiniteException.class)
    @ResponseBody
    public final ResponseEntity<Object> handleRoomInfiniteException(RuntimeException ex) {
        ErrorResponse response = new ErrorResponse(ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(WallsDiagonalException.class)
    @ResponseBody
    public final ResponseEntity<Object> handleDiagonalWallsException(RuntimeException ex) {
        ErrorResponse response = new ErrorResponse(ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(WallsIntersectException.class)
    @ResponseBody
    public final ResponseEntity<Object> handleWallsIntersectException(RuntimeException ex) {
        ErrorResponse response = new ErrorResponse(ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(NumberOfAnglesException.class)
    @ResponseBody
    public final ResponseEntity<Object> handleNumberOfAnglesException(RuntimeException ex) {
        ErrorResponse response = new ErrorResponse(ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(RoomNotFoundException.class)
    @ResponseBody
    public final ResponseEntity<Object> handleRoomNotFoundException(RuntimeException ex) {
        ErrorResponse response = new ErrorResponse(ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(NumberNotIntegerException.class)
    @ResponseBody
    public final ResponseEntity<Object> handleIntegerException(RuntimeException ex) {
        ErrorResponse response = new ErrorResponse(ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
}
