package lab9.homework;

import lab9.homework.models.Genre;

public class GenreRepository extends EntityRepository<Genre> {
    public GenreRepository(Class<Genre> entityType) {
        super(entityType);
    }

    public void createGenre(Genre genre) {
        create(genre);
    }

    public Genre findById(long id) {
        return super.findById(id);
    }

    public Genre findByName(String name) {
        return super.findByName(name);
    }
}
