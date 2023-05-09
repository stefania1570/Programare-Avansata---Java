package lab9.compulsory;

public class EntityRepository <T> {
    public void create(T entity) {
        DatabaseEntity.getEntityManager().getTransaction().begin();
        DatabaseEntity.getEntityManager().persist(entity);
        DatabaseEntity.getEntityManager().getTransaction().commit();
    }

    public T findById(long id) {
        return (T)DatabaseEntity
                .getEntityManager()
                .createNamedQuery("Genres.findById")
                .setParameter(1, id)
                .getSingleResult();
    }

    public T findByName(String name) {
        return (T)DatabaseEntity
                .getEntityManager()
                .createNamedQuery("Genres.findByName")
                .setParameter(1, name)
                .getSingleResult();
    }
}
