package hu.alkfejl.dao;

import hu.alkfejl.model.Room;

import java.util.List;

public interface RoomDAO {

    List<Room> findAll();

    List<Room> findAllByName(String name);

    List<Room> findAllByCategory(String category);

    Room findById(int id);

    Room save(Room room);

    void delete(Room room);
}
