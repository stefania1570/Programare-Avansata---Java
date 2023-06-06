package Databases;

import java.sql.*;

public class UserDAO {
    public void create(int id, String username, String password) throws SQLException {
        Connection connection = DatabaseConnection.getConnection();
        try(PreparedStatement pstmt = connection.prepareStatement(
                "INSERT INTO users (username, password) VALUES ( ?, ?)"
        )){
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            pstmt.executeUpdate();
        } catch (SQLException e)
        {
            e.printStackTrace();
        }finally {
            connection.close();
        }
    }

    public int getIdByUsername(String username) throws SQLException {
        Connection connection = DatabaseConnection.getConnection();
        int id = 0;
        try(Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery(
                    "SELECT id FROM users WHERE username='" + username + "'"
            )){
            while (resultSet.next())
            {
                id = Integer.parseInt(resultSet.getString(1));
            }
        }catch (SQLException e)
        {
            e.printStackTrace();
        }finally {
            connection.close();
        }
        return id;
    }

    public String getUsernameById(int id) throws SQLException {
        Connection connection = DatabaseConnection.getConnection();
        String username = "";
        try(Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery(
                    "SELECT username FROM users WHERE id=" + id
            )){
            while (resultSet.next())
            {
                username = resultSet.getString(1);
            }
        }catch (SQLException e)
        {
            e.printStackTrace();
        }finally {
            connection.close();
        }
        return username;
    }

    public boolean findUser(String username, String password) throws SQLException {
        Connection connection = DatabaseConnection.getConnection();
        int id = 0;
        try(Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery(
                    "SELECT id FROM users WHERE username='" + username + "' and password='" + password + "'"
            )){
            while (resultSet.next())
            {
                id = Integer.parseInt(resultSet.getString(1));
            }
        }catch (SQLException e)
        {
            e.printStackTrace();
        }finally {
            connection.close();
        }
        if(id == 0) return false;
        return true;
    }
}
