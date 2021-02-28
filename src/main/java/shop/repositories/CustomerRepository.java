package shop.repositories;

import lombok.Data;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import shop.entities.Customer;
import javax.persistence.EntityManagerFactory;
import java.util.List;

@Component
@Data
public class CustomerRepository implements AutoCloseable {

    private SessionFactory sessionFactory;
    private Session session;

    @Autowired
    public CustomerRepository(EntityManagerFactory factory) {
        if (factory.unwrap(SessionFactory.class) == null) {
            throw new NullPointerException("factory is not a hibernate factory");
        }
        this.sessionFactory = factory.unwrap(SessionFactory.class);
        session = sessionFactory.getCurrentSession();
    }

    @Override
    public void close() throws Exception {
        if (session != null) session.close();
    }

    public List<Customer> getAll() {
        return session
                .createQuery("select c from Customer c", Customer.class)
                .getResultList();
    }

}
