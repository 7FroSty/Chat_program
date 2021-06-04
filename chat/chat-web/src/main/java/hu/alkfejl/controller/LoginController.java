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


@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public LoginController() {
        super();
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");

        UserDAO userDAO = UserDAOImpl.getInstance();

        String email = req.getParameter("email");
        String password = req.getParameter("password");

        User user = userDAO.login(email, password);

        if(user == null){
            resp.sendRedirect("pages/login.jsp");
            return;
        }

        req.getSession().setAttribute("currentUser", user);
        req.getRequestDispatcher("/ListRoomsController").forward(req, resp);
    }
}
