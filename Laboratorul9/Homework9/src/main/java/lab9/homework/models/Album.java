package lab9.homework.models;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "albums")
@NamedQueries({
        @NamedQuery(name = "Album.findAll", query = "select album from Album album order by album.releaseYear, album.title"),
        @NamedQuery(name = "Album.findById", query = "select album from Album album where album.id = ?1"),
        @NamedQuery(name = "Album.findByTitle", query = "select album from Album album where album.title = ?1"),
})
public class Album {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "release_year")
    private int releaseYear;
    @Basic
    @Column(name = "title")
    private String title;

    @ManyToOne
    @JoinColumn(name = "artist", referencedColumnName = "id")
    private Artist artist;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "albums_genres",
            joinColumns = @JoinColumn(name = "album_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    private Set<Genre> genres = new HashSet<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public Set<Genre> getGenres() {
        return genres;
    }

    public void addGenre(Genre genre) {
        genres.add(genre);
        genre.getAlbums().add(this);
    }

    public void removeGenre(Genre genre) {
        genres.remove(genre);
        genre.getAlbums().remove(this);
    }
}


