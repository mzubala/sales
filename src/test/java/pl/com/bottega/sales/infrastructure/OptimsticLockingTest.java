package pl.com.bottega.sales.infrastructure;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;
import pl.com.bottega.common.domain.Address;
import pl.com.bottega.common.domain.Money;
import pl.com.bottega.sales.domain.CustomerData;
import pl.com.bottega.sales.domain.Order;
import pl.com.bottega.sales.domain.ProductData;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@SpringBootTest
@RunWith(SpringRunner.class)
public class OptimsticLockingTest {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private TransactionTemplate tt;


    @Test
    @Transactional
    public void shouldLock() {
        Address address = new Address("Fabryczna 10", "", "20-100", "Wrocław", "DW", "PL");
        Order order = new Order(new CustomerData("Jan", "Nowak", address, address));
        order.addItem(new ProductData(1L, "Kalesony", new Money(50.0)), 5);
        em.persist(order);
    }


    @Test
    public void shouldLockOnUpdate() {
        Address address = new Address("Fabryczna 10", "", "20-100", "Wrocław", "DW", "PL");
        Order order = new Order(new CustomerData("Jan", "Nowak", address, address));
        order.addItem(new ProductData(1L, "Kalesony", new Money(50.0)), 5);
        tt.execute((c) -> {
            em.persist(order);
            return null;
        });

        tt.execute((c) -> {
            Order order2 = em.find(Order.class, 1L);
            order2.addItem(new ProductData(1L, "Kalesony", new Money(50.0)), 5);
            em.persist(order2);
            return null;
        });
    }

}
