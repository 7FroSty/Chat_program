package hu.alkfejl.dao;

import hu.alkfejl.config.ChatConfiguration;
import hu.alkfejl.model.Comment;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class CommentDAOImpl implements CommentDAO {


    private static final String INSERT_COMMENT = "INSERT INTO COMMENT (user_id, room_id, message, picture) VALUES (?, ?, ?, ?)";
    private static CommentDAO instance;
    private String connectionURL;
    UserDAO userDAO = UserDAOImpl.getInstance();

    public CommentDAOImpl() {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        connectionURL = ChatConfiguration.getValue("db.url");
    }

    public static CommentDAO getInstance() {
        if(instance == null){
            instance = new CommentDAOImpl();
        }
        return instance;
    }

    @Override
    public List<Comment> findAllByRoomId(int roomId){

        List<Comment> result = new ArrayList<>();

        try (Connection c = DriverManager.getConnection(connectionURL);
             PreparedStatement stmt = c.prepareStatement("SELECT * FROM COMMENT WHERE room_id = ?")
        ){
            stmt.setInt(1, roomId);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                Comment comment = extractCommentFromResultSet(rs);
                result.add(comment);

            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return result;
    }

    @Override
    public Comment save(Comment comment){

        try(Connection c = DriverManager.getConnection(connectionURL);
            PreparedStatement stmt = c.prepareStatement(INSERT_COMMENT, Statement.RETURN_GENERATED_KEYS)
        ){
            if(comment.getUser() != null){
                stmt.setInt(1, comment.getUser().getId());
            }
            stmt.setInt(2, comment.getRoomId());
            stmt.setString(3, comment.getMessage());
            stmt.setString(4, comment.getPicture());

            int affectedRows = stmt.executeUpdate();
            if(affectedRows == 0){
                return null;
            }

            if(comment.getId() <= 0){ // INSERT
                ResultSet genKeys = stmt.getGeneratedKeys();
                if(genKeys.next()){
                    comment.setId(genKeys.getInt(1));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        return comment;
    }

    private Comment extractCommentFromResultSet(ResultSet rs) {
        try {
            Comment comment = new Comment();
            comment.setId(rs.getInt("id"));
            comment.setMessage(rs.getString("message"));
            comment.setPicture(rs.getString("picture"));
            comment.setUser(userDAO.getUserById(rs.getInt("user_id")));

            return comment;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
}
