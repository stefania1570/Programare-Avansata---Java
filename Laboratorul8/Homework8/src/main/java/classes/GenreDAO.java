package classes;

import java.sql.*;

public class GenreDAO {

    public void create(String name) throws SQLException {
        Connection con = Database.getConnection();
        try (PreparedStatement statement = con.prepareStatement(
                "insert into genres (name) values (?)")) {
            statement.setString(1, name);
            statement.executeUpdate();
        }
    }
    static public Integer findByName(String name) throws SQLException {
        Connection con = Database.getConnection();
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(
                     "select id from genres where name='" + name + "'")) {
            return rs.next() ? rs.getInt(1) : null;
        }
    }

    static public String findById(int id) throws SQLException {
        Connection con = Database.getConnection();
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(
                     "select name from genres where id=" + id)) {
            return rs.next() ? rs.getString(1) : null;
        }
    }
}
