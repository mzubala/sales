package pl.com.bottega.infrastructure;

import org.hibernate.Session;
import org.hibernate.stat.SecondLevelCacheStatistics;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.support.TransactionTemplate;
import pl.com.bottega.common.domain.Address;
import pl.com.bottega.common.domain.Money;
import pl.com.bottega.sales.domain.Customer;
import pl.com.bottega.sales.domain.CustomerData;
import pl.com.bottega.sales.domain.Order;
import pl.com.bottega.sales.domain.ProductData;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@RunWith(SpringRunner.class)
public class L2CacheTest {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private TransactionTemplate tt;

    @Test
    public void shouldCacheCustomer() {
        for (int i = 0; i < 500; i++) {
            Address address = new Address("Fabryczna 10", "", "20-100", "Wrocław", "DW", "PL");
            Order order = new Order(new CustomerData("Jan", "Nowak", address, address));
            order.addItem(new ProductData(1L, "Kalesony", new Money(50.0)), 5);
            tt.execute((c) -> {
                em.persist(order);
                return null;
            });
        }

        tt.execute((c) -> {
            Session session = em.unwrap(Session.class);
            Order o = session.get(Order.class, 1L);
            SecondLevelCacheStatistics stats = session.getSessionFactory().getStatistics().getSecondLevelCacheStatistics(Order.class.getName());
            assertThat(stats.getPutCount()).isEqualTo(1);
            return null;
        });

        tt.execute((c) -> {
            Session session = em.unwrap(Session.class);
            Order o = session.get(Order.class, 1L);
            SecondLevelCacheStatistics stats = session.getSessionFactory().getStatistics().getSecondLevelCacheStatistics(Order.class.getName());
            assertThat(stats.getHitCount()).isEqualTo(1);
            return null;
        });

    }
}
