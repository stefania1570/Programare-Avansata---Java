package lab9.homework;

import lab9.homework.models.Album;

public class AlbumRepository extends EntityRepository<Album> {
    public AlbumRepository() {
        super(Album.class);
    }
}