package lab9.homework;

import lab9.homework.models.Artist;

public class ArtistRepository extends EntityRepository<Artist> {
    public ArtistRepository() {
        super(Artist.class);
    }
}