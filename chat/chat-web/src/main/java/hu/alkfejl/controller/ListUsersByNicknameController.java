package hu.alkfejl.controller;

import hu.alkfejl.dao.UserDAO;
import hu.alkfejl.dao.UserDAOImpl;
import hu.alkfejl.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/ListUsersByNicknameController")
public class ListUsersByNicknameController extends HttpServlet {

    private UserDAO dao = UserDAOImpl.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");

        String nickname = req.getParameter("nickname");

        List<User> allNicknames = dao.findAllByNickname(nickname);

        req.setAttribute("userList", allNicknames);
        req.getRequestDispatcher("pages/list-users.jsp").forward(req, resp);
    }
}