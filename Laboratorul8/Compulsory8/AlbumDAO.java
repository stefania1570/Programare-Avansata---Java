package org.example;

import java.sql.*;

public class AlbumDAO {
    public void create(int releaseYear, String title, String artist, String genres) throws SQLException {
        Connection con = Database.getConnection();
        try (PreparedStatement pstmt = con.prepareStatement(
                "insert into albums (release_year, title, artist, genres) values (?, ?, ?, ?)")) {
            pstmt.setInt(1, releaseYear);
            pstmt.setString(2, title);
            pstmt.setString(3, artist);
            pstmt.setString(4, genres);
            pstmt.executeUpdate();
        }
    }

    public void printAllAlbums() throws SQLException {
        Connection con = Database.getConnection();
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("select * from albums")) {

        }
    }
}