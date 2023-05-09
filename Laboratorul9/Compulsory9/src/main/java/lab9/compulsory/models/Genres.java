package lab9.compulsory.models;
import jakarta.persistence.*;

@Entity
@Table(name = "genres")
@NamedQueries({
        @NamedQuery(name="Genres.findAll", query = "select genre from Genres genre order by genre.name"),
        @NamedQuery(name="Genres.findById", query = "select genre from Genres genre where genre.id = ?1"),
        @NamedQuery(name="Genres.findByName", query = "select genre from Genres genre where genre.name = ?1"),
})
public class Genres {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "name")
    private String name;
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

}
