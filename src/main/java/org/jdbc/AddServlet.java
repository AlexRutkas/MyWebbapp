package org.jdbc;

import org.db.ConnectionFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

public class AddServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("pages/add.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String insertTableSQL = "INSERT INTO workers"
                + "(name, surname) " + "VALUES"
                + "(?, ?)";

        PrintWriter pw = resp.getWriter();



        String name = req.getParameter("name");
        String surname = req.getParameter("surname");

        Connection connection = ConnectionFactory.getInstance().getConnectoin(req);
        try {


            try(PreparedStatement preparedStatement = connection
                    .prepareStatement(insertTableSQL)) {
                preparedStatement.setString(1, name);
                preparedStatement.setString(2, surname);
                int rows = preparedStatement.executeUpdate();
                pw.printf("%d rows added", rows);
                preparedStatement.close();
            }
        } catch (SQLException throwables){
            throwables.printStackTrace();
        }
    }
}
