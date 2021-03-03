package shop.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import shop.models.Customer;
import javax.persistence.EntityManagerFactory;

@Component
public class CustomerRepository extends EntityRepository {

    @Autowired
    public CustomerRepository(EntityManagerFactory factory) {
        super(factory, Customer.class);
    }

}
