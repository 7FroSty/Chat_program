package hu.alkfejl.dao;

import hu.alkfejl.model.Room;
import hu.alkfejl.model.User;

import java.util.List;

public interface UserDAO {

    List<User> findAll();

    List<User> findAllByNickname(String nickname);

    List<User> findAllByInterest(String interest);

    User getUserById(int id);

    User save(User user);

    void delete(User user);

    User login(String email, String password);
}
