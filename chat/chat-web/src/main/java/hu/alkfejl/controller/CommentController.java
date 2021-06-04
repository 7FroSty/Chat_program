package hu.alkfejl.controller;

import hu.alkfejl.dao.CommentDAO;
import hu.alkfejl.dao.CommentDAOImpl;
import hu.alkfejl.model.Comment;
import hu.alkfejl.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Base64;
import java.util.List;


@WebServlet("/CommentController")
public class CommentController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private CommentDAO dao = CommentDAOImpl.getInstance();

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");

        Comment comment = new Comment();

        if(req.getParameter("message") != null && !req.getParameter("message").isEmpty()){
            comment.setMessage(req.getParameter("message"));
        }

        if(req.getParameter("picture") != null && !req.getParameter("picture").isEmpty()){
            comment.setPicture(req.getParameter("picture"));
        }

        comment.setRoomId(Integer.parseInt(req.getParameter("roomId")));

        User currentUser = (User) req.getSession().getAttribute("currentUser");
        comment.setUser(currentUser);


        dao.save(comment);

        req.getRequestDispatcher("/ChatRoomController").forward(req, resp);
    }
}
