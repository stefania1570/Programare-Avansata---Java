package lab9.homework;

public class EntityRepository<T> {
    private Class<T> entityType;

    public EntityRepository(Class<T> entityType) {
        this.entityType = entityType;
    }

    public void create(T entity) {
        DatabaseEntity.getEntityManager().getTransaction().begin();
        DatabaseEntity.getEntityManager().persist(entity);
        DatabaseEntity.getEntityManager().getTransaction().commit();
    }

    public T findById(long id) {
        String queryName = entityType.getSimpleName() + ".findById";
        return (T) DatabaseEntity
                .getEntityManager()
                .createNamedQuery(queryName)
                .setParameter(1, id)
                .getSingleResult();
    }

    public T findByName(String name) {
        String queryName = entityType.getSimpleName() + ".findByName";
        return (T) DatabaseEntity
                .getEntityManager()
                .createNamedQuery(queryName)
                .setParameter(1, name)
                .getSingleResult();
    }

}
