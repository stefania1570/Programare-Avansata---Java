package lab9.compulsory.models;
import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "albums_genre_assoc", schema = "public", catalog = "albums")
@IdClass(AlbumsGenreAssoc.class)
public class AlbumsGenreAssoc implements Serializable {
    @Id
    @Basic
    @Column(name = "album_id")
    private Integer id_album;
    @Id
    @Basic
    @Column(name = "genre_id")
    private Integer id_genre;

    public Integer getId_album() {
        return id_album;
    }
    public void setId_album(Integer id_album) {
        this.id_album = id_album;
    }
    public Integer getId_genre() {
        return id_genre;
    }
    public void setId_genre(Integer id_genre) {
        this.id_genre = id_genre;
    }

}
