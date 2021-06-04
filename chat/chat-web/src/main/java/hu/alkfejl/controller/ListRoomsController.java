package hu.alkfejl.controller;

import hu.alkfejl.dao.RoomDAO;
import hu.alkfejl.dao.RoomDAOImpl;
import hu.alkfejl.model.Room;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet("/ListRoomsController")
public class ListRoomsController extends HttpServlet {

    private RoomDAO dao = RoomDAOImpl.getInstance();

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Room> all = dao.findAll();
        req.setAttribute("roomList", all);
        req.getRequestDispatcher("pages/list-rooms.jsp").forward(req, resp);
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Room> all = dao.findAll();
        req.setAttribute("roomList", all);
        req.getRequestDispatcher("pages/list-rooms.jsp").forward(req, resp);
    }
}
