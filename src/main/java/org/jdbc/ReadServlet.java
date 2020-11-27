package org.jdbc;

import org.db.ConnectionFactory;
import org.db.ConnectionFactory_1;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

public class ReadServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter pw = resp.getWriter();
        Connection connection = ConnectionFactory.getInstance().getConnectoin(req);
               try {
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM workers");//table User

            while (resultSet.next()) {

                pw.println(resultSet.getString("name")+" - "+resultSet.getString("surname"));

            }
            statement.close();
            connection.close();
        } catch (SQLException throwables){
            throwables.printStackTrace();
        }
    }
}
