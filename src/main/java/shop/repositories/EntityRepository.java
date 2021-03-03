package shop.repositories;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.EntityManagerFactory;
import java.util.List;

public abstract class EntityRepository<E> {

    protected SessionFactory sessionFactory;
    private final Class<shop.models.Customer> entityClass;

    public EntityRepository(EntityManagerFactory factory, Class<shop.models.Customer> entityClass) {
        this.sessionFactory = factory.unwrap(SessionFactory.class);
        if (this.sessionFactory == null) {
            throw new NullPointerException("factory is not a hibernate factory");
        }
        this.entityClass = entityClass;
    }

    protected List<E> getQueryResult(String query) {
        Session session = sessionFactory.getCurrentSession();
        List<E> list = null;
        try {
            session.beginTransaction();
            list = (List<E>) session
                    .createQuery(query, entityClass)
                    .getResultList();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return list;
    }

    public List<E> getAll() {
        return getQueryResult(String.format("select c from %s c", entityClass.getSimpleName()));
    }

}
