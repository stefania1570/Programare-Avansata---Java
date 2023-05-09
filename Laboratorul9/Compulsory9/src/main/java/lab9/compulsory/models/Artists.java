package lab9.compulsory.models;
import jakarta.persistence.*;

@Entity
@Table(name = "artists")
@NamedQueries({
        @NamedQuery(name="Artists.findAll", query = "select artist from Artists artist order by artist.name"),
})
public class Artists {
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
