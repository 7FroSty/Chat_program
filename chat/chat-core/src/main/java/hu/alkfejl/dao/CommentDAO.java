package hu.alkfejl.dao;

import hu.alkfejl.model.Comment;

import java.util.List;

public interface CommentDAO {

    List<Comment> findAllByRoomId(int roomId);

    Comment save(Comment comment);
}
