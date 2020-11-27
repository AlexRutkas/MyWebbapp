package org.jdbc;

import org.db.ConnectionFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateTabServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String createTableSQL = "CREATE TABLE DBUSER("
                + "USER_ID BIGINT NOT NULL, "
                + "USERNAME VARCHAR(20) NOT NULL, "
                + "CREATED_BY VARCHAR(20) NOT NULL, "
                + "CREATED_DATE DATE NOT NULL, " + "PRIMARY KEY (USER_ID) "
                + ")";

        PrintWriter pw = resp.getWriter();

        Connection connection = ConnectionFactory.getInstance().getConnectoin(req);
        try {


            Statement statement = connection.createStatement();
            statement.executeQuery(createTableSQL);//table User
            statement.close();

        } catch (SQLException throwables){
            throwables.printStackTrace();
        }
    }
}
