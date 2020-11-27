package org.db;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.sql.*;
import java.util.Properties;

public class ConnectionFactory {
    private   static ConnectionFactory instance = null;
    //  Database credentials

    static  String url, username, password;
    private ConnectionFactory() {

    }

    public static ConnectionFactory getInstance() {

        if (instance == null) instance = new ConnectionFactory();
        return instance;
    }

    public  Connection getConnectoin(HttpServletRequest request) {

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }

        Connection connection = null;


        try {
            loadProperties(request);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            connection = DriverManager
                    .getConnection(url, username, password);

        } catch (SQLException e) {
            System.out.println("Connection Failed");
            e.printStackTrace();
        }

        if (connection != null) {
            System.out.println("You successfully connected to database now");

        } else {
            System.out.println("Failed to make connection to database");
        }
        return connection;
    }

    public void loadProperties(HttpServletRequest request) throws FileNotFoundException, IOException
    {


        Properties properties = new Properties();

        InputStream input = request.getSession().getServletContext().
                getResourceAsStream("/WEB-INF/db.properties");

        properties.load(input);

        url      = properties.getProperty("url");
        username = properties.getProperty("user");
        password = properties.getProperty("password");
    }
}