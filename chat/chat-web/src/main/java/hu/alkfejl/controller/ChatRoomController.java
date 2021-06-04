package hu.alkfejl.controller;

import hu.alkfejl.dao.CommentDAO;
import hu.alkfejl.dao.CommentDAOImpl;
import hu.alkfejl.dao.RoomDAO;
import hu.alkfejl.dao.RoomDAOImpl;
import hu.alkfejl.model.Comment;
import hu.alkfejl.model.Room;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/ChatRoomController")
public class ChatRoomController extends HttpServlet {
    private RoomDAO dao = RoomDAOImpl.getInstance();
    private CommentDAO commentDAO = CommentDAOImpl.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idStr = req.getParameter("roomId");

        if(idStr != null && !idStr.isEmpty()){
            int id = Integer.parseInt(idStr);
            Room room = dao.findById(id);
            req.setAttribute("room", room);
            List<Comment> all = commentDAO.findAllByRoomId(id);
            req.setAttribute("commentList", all);
        }

        req.getRequestDispatcher("pages/chat-room.jsp").forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idStr = req.getParameter("roomId");

        if(idStr != null && !idStr.isEmpty()){
            int id = Integer.parseInt(idStr);
            Room room = dao.findById(id);
            req.setAttribute("room", room);
            List<Comment> all = commentDAO.findAllByRoomId(id);
            req.setAttribute("commentList", all);
        }

        req.getRequestDispatcher("pages/chat-room.jsp").forward(req, resp);
    }
}
