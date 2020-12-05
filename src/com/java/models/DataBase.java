package com.java.models;

import com.java.exceptions.CityNotExistException;
import com.java.exceptions.WrongPasswordException;

import java.sql.*;

public class DataBase {
    private static DataBase instance;

    private static Connection con;
    private static Statement stmt;
    private static ResultSet rs;

    private DataBase() {
        try {
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection("jdbc:sqlite:weather.db");
            stmt = con.createStatement();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static DataBase getInstance() {
        if (instance == null) {
            instance = new DataBase();
        }
        return instance;
    }

    public void close() {
        try {
            con.close();
            stmt.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Double[] getCoordsByCity(String cityName) throws CityNotExistException {
        Double lat = null;
        Double lon = null;

        String query = String.format(
                "select `owm_latitude`, `owm_longitude` " +
                "from `owm_city_list` " +
                "where `owm_city_name` like '%s%s%s'",
                "%", cityName, "%"
        );

        try {
            rs = stmt.executeQuery(query);
            lat = rs.getDouble("owm_latitude");
            lon = rs.getDouble("owm_longitude");
        } catch (SQLException e) {
            throw new CityNotExistException();  // город не найден
        }
        return new Double[]{lat, lon};
    }

    public String getCity(String cityName) throws CityNotExistException {
        String query = String.format(
                "select `owm_city_name` " +
                        "from `owm_city_list` " +
                        "where `owm_city_name` like '%s%s%s'",
                "%", cityName, "%"
        );

        try {
            rs = stmt.executeQuery(query);
            cityName = rs.getString("owm_city_name");
        } catch (SQLException e) {
            throw new CityNotExistException();  // город не найден
        }
        return cityName;
    }

    public User getUser(String username, String password) throws WrongPasswordException {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);

        String query = String.format(
                "select `username`, `password`, `city`, `service_id` " +
                "from `users` " +
                "where `username` = '%s'",
                user.getUsername()
        );

        try {
            rs = stmt.executeQuery(query);
            user.setCity(rs.getString("city"));
            user.setServiceId(rs.getInt("service_id"));

            if (user.getPassword().equals(rs.getString("password"))) {
                return user;
            } else
                throw new WrongPasswordException();  // пароли не совпадают
        } catch (SQLException e) {
            createUser(user);  // неявная регистрация
        }

        return user;
    }

    public void setCity(User user) {
        String query = String.format(
                "update `users` " +
                "set `city` = '%s' " +
                "where `username` = '%s'",
                user.getCity(), user.getUsername()
        );

        try {
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setServiceId(User user) {
        String query = String.format(
                "update `users` " +
                "set `service_id` = '%s' " +
                "where `username` = '%s'",
                user.getServiceId(), user.getUsername()
        );

        try {
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void createUser(User user) {
        String query = String.format(
                "insert into `users` ('username', 'password') " +
                "values ('%s', '%s')",
                user.getUsername(), user.getPassword()
        );

        try {
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
