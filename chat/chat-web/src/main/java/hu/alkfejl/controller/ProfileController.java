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

@WebServlet("/ProfileController")
public class ProfileController extends HttpServlet {

    private UserDAO dao = UserDAOImpl.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        User user = (User) req.getSession().getAttribute("currentUser");

        if(req.getParameter("email") != null && !req.getParameter("email").isEmpty()){
            user.setEmail(req.getParameter("email"));
        }

        if(req.getParameter("nickname") != null && !req.getParameter("nickname").isEmpty()){
            user.setNickname(req.getParameter("nickname"));
        }

        if(req.getParameter("age") != null && !req.getParameter("age").isEmpty()){
            user.setAge(Integer.parseInt(req.getParameter("age")));
        }

        if(req.getParameter("interest") != null && !req.getParameter("interest").isEmpty()){
            user.setInterest(req.getParameter("interest"));
        }

        user = dao.save(user);

        req.getSession().setAttribute("currentUser", user);
        resp.sendRedirect("pages/profile.jsp");
    }
}
