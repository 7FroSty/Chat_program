package hu.alkfejl.dao;

import hu.alkfejl.config.ChatConfiguration;
import hu.alkfejl.model.Room;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RoomDAOImpl implements RoomDAO {

    private static final String SELECT_ALL_ROOMS = "SELECT * FROM ROOM";
    private static final String INSERT_ROOM = "INSERT INTO ROOM (name, rule, category) VALUES (?,?,?)";
    private static final String UPDATE_ROOM = "UPDATE ROOM SET name = ?, rule = ?, category = ?, position = ? WHERE id=?";
    private static final String DELETE_ROOM = "DELETE FROM ROOM WHERE id = ?";
    private static RoomDAO instance;
    private String connectionURL;

    public RoomDAOImpl() {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        connectionURL = ChatConfiguration.getValue("db.url");
    }

    public static RoomDAO getInstance() {
        if(instance == null){
            instance = new RoomDAOImpl();
        }
        return instance;
    }

    @Override
    public List<Room> findAll() {

        List<Room> result = new ArrayList<>();

        try (Connection c = DriverManager.getConnection(connectionURL);
             Statement stmt = c.createStatement();
             ResultSet rs = stmt.executeQuery(SELECT_ALL_ROOMS)
        ){
            while(rs.next()){
                Room room = new Room();
                room.setId(rs.getInt("id"));
                room.setName(rs.getString("name"));
                room.setRule(rs.getString("rule"));
                room.setCategory(rs.getString("category"));

                result.add(room);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;

    }

    @Override
    public List<Room> findAllByName(String name) {

        List<Room> result = new ArrayList<>();

        try (Connection c = DriverManager.getConnection(connectionURL);
             Statement stmt = c.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM ROOM WHERE name LIKE '%" + name + "%'")
        ){
            while(rs.next()){
                Room room = new Room();
                room.setId(rs.getInt("id"));
                room.setName(rs.getString("name"));
                room.setRule(rs.getString("rule"));
                room.setCategory(rs.getString("category"));

                result.add(room);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;

    }

    @Override
    public List<Room> findAllByCategory(String category) {

        List<Room> result = new ArrayList<>();

        try (Connection c = DriverManager.getConnection(connectionURL);
             Statement stmt = c.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM ROOM WHERE category LIKE '%" + category + "%'")
        ){
            while(rs.next()){
                Room room = new Room();
                room.setId(rs.getInt("id"));
                room.setName(rs.getString("name"));
                room.setRule(rs.getString("rule"));
                room.setCategory(rs.getString("category"));

                result.add(room);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;

    }

    @Override
    public Room findById(int id){

        try (Connection c = DriverManager.getConnection(connectionURL);
             PreparedStatement stmt = c.prepareStatement("SELECT * FROM ROOM WHERE id = ?")
        ){
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return extractContactFromResultSet(rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;

    }

    Room extractContactFromResultSet(ResultSet rs){
        try {
            Room room = new Room();
            room.setId(rs.getInt("id"));
            room.setName(rs.getString("name"));
            room.setRule(rs.getString("rule"));
            room.setCategory(rs.getString("category"));
            return room;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public Room save(Room room) {

        try(Connection c = DriverManager.getConnection(connectionURL);
            PreparedStatement stmt = room.getId() <= 0 ? c.prepareStatement(INSERT_ROOM, Statement.RETURN_GENERATED_KEYS) : c.prepareStatement(UPDATE_ROOM)
        ){
            if(room.getId() > 0){ // UPDATE
                stmt.setInt(4, room.getId());
            }

            stmt.setString(1, room.getName());
            stmt.setString(2, room.getRule());
            stmt.setString(3, room.getCategory());

            int affectedRows = stmt.executeUpdate();
            if(affectedRows == 0){
                return null;
            }

            if(room.getId() <= 0){ // INSERT
                ResultSet genKeys = stmt.getGeneratedKeys();
                if(genKeys.next()){
                    room.setId(genKeys.getInt(1));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        return room;
    }

    @Override
    public void delete(Room room) {

        try(Connection c = DriverManager.getConnection(connectionURL);
            PreparedStatement stmt = c.prepareStatement(DELETE_ROOM);
        ) {
            stmt.setInt(1, room.getId());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
