package shop.repositories;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import shop.models.CustOrder;

import javax.persistence.EntityManagerFactory;
import java.util.ArrayList;
import java.util.List;

@Component
public class CustOrderRepository extends EntityRepository {

    @Autowired
    public CustOrderRepository(EntityManagerFactory factory) {
        super(factory, CustOrder.class);
    }

    public List<CustOrder> getFilteredOrders(int custId, int productId) {
        Session session = sessionFactory.getCurrentSession();
        ArrayList<String> queryCondition = new ArrayList<>();
        String queryString = "select c from CustOrder c";
        if (custId > 0)
            queryCondition.add(String.format("c.customerId = %d", custId));
        if (productId > 0)
            queryCondition.add(String.format("c.productId = %d", productId));
        for (int i = 0; i < queryCondition.size(); i++) {
            queryString = (i == 0)?
                    queryString + " where " + queryCondition.get(i):
                    queryString + " and " + queryCondition.get(i);
        }
        List<CustOrder> list = null;
        try {
            session.beginTransaction();
            list = (List<CustOrder>) session
                    .createQuery(String.format(
                            queryString,
                            custId, productId),
                            CustOrder.class)
                    .getResultList();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return list;
    }

}
