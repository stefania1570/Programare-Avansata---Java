package lab9.compulsory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class DatabaseEntity {
    private static EntityManagerFactory entityManagerFactory = null;
    private static EntityManager entityManager = null;
    private DatabaseEntity(){
        //singleton
    }


    public static EntityManager getEntityManager() {
        if(entityManager == null)
        {
            entityManagerFactory = Persistence.createEntityManagerFactory("x");
            entityManager = entityManagerFactory.createEntityManager();
        }
        return entityManager;
    }
}
