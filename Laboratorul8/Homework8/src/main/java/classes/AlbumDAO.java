package classes;

import models.implementation.Album;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlbumDAO implements GenericDAO<Album>{
    public Album createObjectFromResultSet(ResultSet rs) throws  SQLException {
        int id = rs.getInt("id");
        int releaseYear = rs.getInt("release_year");
        String title = rs.getString("title");
        String artist = rs.getString("artist");


        return new Album(id, releaseYear, title, artist);
    }
    @Override
    public void create(Album album) throws SQLException {
        Connection con = Database.getConnection();
        try {
            PreparedStatement statement = con.prepareStatement("insert into albums (title, release_year, artist) values (?,?,?)");
            statement.setString(1, album.getTitle());
            statement.setInt(2, album.getReleaseYear());
            statement.setString(3, album.getArtist());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public List<Album> findAll() throws SQLException {
        List<Album> albums = new ArrayList<>();
        Connection con = Database.getConnection();
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("select * from albums")) {
            while (rs.next()) {
                int id = rs.getInt("id");
                int releaseYear = rs.getInt("release_year");
                String title = rs.getString("title");
                String artist = rs.getString("artist");
                Album album = new Album(id, releaseYear, title, artist);
                albums.add(album);
            }
        }
        return albums;
    }
    @Override
    public Album findById(int id) throws SQLException {
        Album object = null;
        Connection con = Database.getConnection();
        PreparedStatement stmt = con.prepareStatement("select * from albums where id = ?");
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();
        if(rs.next()) {
            object = createObjectFromResultSet(rs);
        }

        return object;
    }
    @Override
    public Album findByName(String title) throws SQLException {
        Connection con = Database.getConnection();
        try (PreparedStatement stmt = con.prepareStatement("SELECT * FROM albums WHERE title=?")) {
            stmt.setString(1, title);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int id = rs.getInt("id");
                    int releaseYear = rs.getInt("release_year");
                    String artist = rs.getString("artist");
                    return new Album(id, releaseYear, title, artist);
                } else {
                    return null;
                }
            }
        }
    }
}
