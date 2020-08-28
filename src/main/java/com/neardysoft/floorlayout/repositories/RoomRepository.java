package com.neardysoft.floorlayout.repositories;

import com.neardysoft.floorlayout.models.Room;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoomRepository extends CrudRepository<Room, Long> {

    Iterable<Room> findAll();

    void deleteById(Long id);
}
