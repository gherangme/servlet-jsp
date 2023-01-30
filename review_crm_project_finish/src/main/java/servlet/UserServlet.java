package servlet;

import model.RoleModel;
import model.UserModel;
import service.RoleService;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "UserServlet", urlPatterns = {"/users","/user-details"})
public class UserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getServletPath();
        switch (url) {
            case "/users":
                UserService userService = new UserService();
                req.setAttribute("users",userService.getAllUsers());
                req.getRequestDispatcher("user-table.jsp").forward(req,resp);
                break;
            case "/user-details":
                req.getRequestDispatcher("user-details.jsp").forward(req,resp);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
