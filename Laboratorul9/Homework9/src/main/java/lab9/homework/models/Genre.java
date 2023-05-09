package lab9.homework.models;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "genres")
@NamedQueries({
        @NamedQuery(name = "Genre.findAll", query = "select genre from Genre genre order by genre.name"),
        @NamedQuery(name = "Genre.findById", query = "select genre from Genre genre where genre.id = ?1"),
        @NamedQuery(name = "Genre.findByName", query = "select genre from Genre genre where genre.name = ?1"),
})
public class Genre {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "name")
    private String name;

    @ManyToMany(mappedBy = "genres")
    private Set<Album> albums = new HashSet<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(Set<Album> albums) {
        this.albums = albums;
    }
}
