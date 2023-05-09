package classes;

import models.implementation.Album;
import models.implementation.Artist;

import java.sql.*;

public class ArtistDAO {
    public void create(String name) throws SQLException {
        Connection con = Database.getConnection();
        try (PreparedStatement statement = con.prepareStatement(
                "insert into artists (name) values (?)")) {
            statement.setString(1, name);
            statement.executeUpdate();
        }
    }
    static public Integer findByName(String name) throws SQLException {
        Connection con = Database.getConnection();
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(
                     "select id from artists where name='" + name + "'")) {
            return rs.next() ? rs.getInt(1) : null;
        }
    }

    static public String findById(int id) throws SQLException {
        Connection con = Database.getConnection();
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(
                     "select name from artists where id=" + id)) {
            return rs.next() ? rs.getString(1) : null;
        }
    }

}