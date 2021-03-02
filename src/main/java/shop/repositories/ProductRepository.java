package shop.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import shop.models.Product;
import javax.persistence.EntityManagerFactory;

@Component
public class ProductRepository extends EntityRepository {

    @Autowired
    public ProductRepository(EntityManagerFactory factory) {
        super(factory, Product.class);
    }

}
