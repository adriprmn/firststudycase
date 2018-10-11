package com.mitrais.cdc.controller;

import com.mitrais.cdc.dao.UserDao;
import com.mitrais.cdc.dao.impl.UserDaoImpl;
import com.mitrais.cdc.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@WebServlet("/users/*")
public class UserServlet extends AbstractController {

    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        String path = getTemplatePath(request.getServletPath()+request.getPathInfo());

        if ("/list".equalsIgnoreCase(request.getPathInfo())){
            UserDao userDao = UserDaoImpl.getInstance();

            String id = request.getParameter("id");
            String username = request.getParameter("username");
            String password = request.getParameter("password");

            if(id.equalsIgnoreCase("0")){
                User u = new User(username, password);
                userDao.save(u);
            }
            else{
                User u = new User(Long.parseLong(id), username, password);
                userDao.update(u);
            }

            List<User> users = userDao.findAll();
            request.setAttribute("users", users);
        }

        RequestDispatcher requestDispatcher = request.getRequestDispatcher(path);
        requestDispatcher.forward(request, response);
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        String path = getTemplatePath(request.getServletPath()+request.getPathInfo());
        UserDao userDao = UserDaoImpl.getInstance();

        if ("/list".equalsIgnoreCase(request.getPathInfo())){
            List<User> users = userDao.findAll();
            request.setAttribute("users", users);
        }

        if ("/form".equalsIgnoreCase(request.getPathInfo())){
            String id = request.getParameter("id");
            if(id != null){
                Optional<User> u = userDao.find(Long.parseLong(id));
                if(u.isPresent()){
                    request.setAttribute("user", u.get());
                }
            }
        }

        if ("/delete".equalsIgnoreCase(request.getPathInfo())){
            String id = request.getParameter("id");
            Optional<User> u = userDao.find(Long.parseLong(id));
            if(u.isPresent()){
                userDao.delete(u.get());
            }
            response.sendRedirect("/rms/users/list");
            return;
        }

        RequestDispatcher requestDispatcher = request.getRequestDispatcher(path);
        requestDispatcher.forward(request, response);

    }
}
