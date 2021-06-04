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

@WebServlet("/ListRoomsByCategoryController")
public class ListRoomsByCategoryController extends HttpServlet {

    private RoomDAO dao = RoomDAOImpl.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");

        String category = req.getParameter("category");

        List<Room> allCategories = dao.findAllByCategory(category);

        req.setAttribute("roomList", allCategories);
        req.getRequestDispatcher("pages/list-rooms.jsp").forward(req, resp);
    }
}
