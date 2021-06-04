package hu.alkfejl.dao;

import at.favre.lib.crypto.bcrypt.BCrypt;
import hu.alkfejl.config.ChatConfiguration;
import hu.alkfejl.model.Room;
import hu.alkfejl.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO {

    private static final String SELECT_ALL_USERS = "SELECT * FROM USER";
    private static final String INSERT_USER = "INSERT INTO USER (email, nickname, age, sex, interest, password) VALUES (?,?,?,?,?,?)";
    private static final String UPDATE_USER = "UPDATE USER SET email = ?, nickname = ?, age = ?, sex = ?, interest = ? WHERE id=?";
    private static final String DELETE_USER = "DELETE FROM USER WHERE id = ?";
    private static UserDAOImpl instance;
    private String connectionURL;

    public UserDAOImpl() {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        connectionURL = ChatConfiguration.getValue("db.url");
    }

    public static UserDAOImpl getInstance() {
        if (instance == null) {
            try {
                Class.forName("org.sqlite.JDBC");
            } catch (ClassNotFoundException e1) {
                e1.printStackTrace();
            }
            instance = new UserDAOImpl();
        }
        return instance;
    }

    @Override
    public List<User> findAll() {

        List<User> result = new ArrayList<>();

        try (Connection c = DriverManager.getConnection(connectionURL);
             Statement stmt = c.createStatement();
             ResultSet rs = stmt.executeQuery(SELECT_ALL_USERS)
        ){
            while(rs.next()){
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setNickname(rs.getString("nickname"));
                user.setAge(rs.getInt("age"));
                user.setSex(rs.getString("sex"));
                user.setInterest(rs.getString("interest"));

                result.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;

    }

    @Override
    public List<User> findAllByNickname(String nickname) {

        List<User> result = new ArrayList<>();

        try (Connection c = DriverManager.getConnection(connectionURL);
             Statement stmt = c.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM USER WHERE nickname LIKE '%" + nickname + "%'")
        ){
            while(rs.next()){
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setNickname(rs.getString("nickname"));
                user.setAge(rs.getInt("age"));
                user.setSex(rs.getString("sex"));
                user.setInterest(rs.getString("interest"));

                result.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;

    }

    @Override
    public List<User> findAllByInterest(String interest) {

        List<User> result = new ArrayList<>();

        try (Connection c = DriverManager.getConnection(connectionURL);
             Statement stmt = c.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM USER WHERE interest LIKE '%" + interest + "%'")
        ){
            while(rs.next()){
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));
                user.setNickname(rs.getString("nickname"));
                user.setAge(rs.getInt("age"));
                user.setSex(rs.getString("sex"));
                user.setInterest(rs.getString("interest"));

                result.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;

    }

    @Override
    public User getUserById(int id) {
        try (Connection conn = DriverManager.getConnection(connectionURL);
             PreparedStatement pst = conn.prepareStatement("SELECT * FROM USER WHERE id = ?")
        ) {
            pst.setInt(1, id);

            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                User user = new User();
                user.setId(rs.getInt(1));
                user.setEmail(rs.getString(2));
                user.setPassword(rs.getString(3));
                user.setNickname(rs.getString(4));
                return user;
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return null;
    }

    @Override
    public User save(User user) {

        try(Connection c = DriverManager.getConnection(connectionURL);
            PreparedStatement stmt = user.getId() <= 0 ? c.prepareStatement(INSERT_USER, Statement.RETURN_GENERATED_KEYS) : c.prepareStatement(UPDATE_USER)
        ){
            stmt.setString(1, user.getEmail());
            stmt.setString(2, user.getNickname());
            stmt.setInt(3, user.getAge());
            stmt.setString(4, user.getSex());
            stmt.setString(5, user.getInterest());

            if(user.getId() > 0){ // UPDATE
                stmt.setInt(6, user.getId());
            }
            else{ // INSERT
                String hashedPwd = BCrypt.withDefaults().hashToString(12, user.getPassword().toCharArray());
                stmt.setString(6, hashedPwd);
            }

            int affectedRows = stmt.executeUpdate();
            if(affectedRows == 0){
                return null;
            }

            if(user.getId() <= 0){ // INSERT
                ResultSet genKeys = stmt.getGeneratedKeys();
                if(genKeys.next()){
                    user.setId(genKeys.getInt(1));
                }
            }

            user.setPassword("");

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        return user;
    }

    @Override
    public void delete(User user) {

        try(Connection c = DriverManager.getConnection(connectionURL);
            PreparedStatement stmt = c.prepareStatement(DELETE_USER);
        ) {
            stmt.setInt(1, user.getId());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User login(String email, String password) {

        try (Connection c = DriverManager.getConnection(connectionURL);
             PreparedStatement stmt = c.prepareStatement("SELECT * FROM USER WHERE email = ?")
        ) {
            stmt.setString(1, email);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {

                String dbPass = rs.getString("password");

                BCrypt.Result result = BCrypt.verifyer().verify(password.toCharArray(), dbPass);
                if(result.verified){
                    User user = new User();
                    user.setEmail(rs.getString("email"));
                    user.setPassword(rs.getString("password"));
                    user.setNickname(rs.getString("nickname"));
                    user.setAge(rs.getInt("age"));
                    user.setSex(rs.getString("sex"));
                    user.setInterest(rs.getString("interest"));
                    user.setId(rs.getInt("id"));
                    return user;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}

