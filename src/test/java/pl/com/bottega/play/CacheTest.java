package pl.com.bottega.play;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.stat.Statistics;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.support.TransactionTemplate;
import pl.com.bottega.common.domain.Money;
import pl.com.bottega.sales.application.PurchaseProcess;
import pl.com.bottega.sales.domain.Customer;
import pl.com.bottega.sales.domain.Product;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CacheTest {

    @Autowired
    private TransactionTemplate tt;

    @PersistenceContext
    private EntityManager em;

    @Test
    public void testReadFromCache() {
        Customer customer = new Customer();
        runInTransaction(() -> em.persist(customer));

        SessionFactory sessionFactory = em.getEntityManagerFactory().unwrap(SessionFactory.class);
        Statistics stats = sessionFactory.getStatistics();
        stats.setStatisticsEnabled(true);

        em.find(Customer.class, customer.getId());
        assertThat(stats.getSecondLevelCachePutCount()).isEqualTo(1L);
        assertThat(stats.getSecondLevelCacheMissCount()).isEqualTo(1L);

        em.find(Customer.class, customer.getId());
        assertThat(stats.getSecondLevelCacheHitCount()).isEqualTo(1L);
        assertThat(stats.getSecondLevelCachePutCount()).isEqualTo(1L);
        assertThat(stats.getSecondLevelCacheMissCount()).isEqualTo(1L);
    }

    @Test
    public void testWriteToCache() {
        Customer customer = new Customer();
        runInTransaction(() -> em.persist(customer));

        SessionFactory sessionFactory = em.getEntityManagerFactory().unwrap(SessionFactory.class);
        Statistics stats = sessionFactory.getStatistics();
        stats.setStatisticsEnabled(true);

        em.find(Customer.class, customer.getId());

        runInTransaction(() -> {
            Customer customer1 = em.find(Customer.class, customer.getId());
            customer1.changeName("Jan", "Nowak");
        });
        assertThat(stats.getSecondLevelCacheHitCount()).isEqualTo(1L);
        assertThat(stats.getSecondLevelCachePutCount()).isEqualTo(2L);

        em.find(Customer.class, customer.getId());
        assertThat(stats.getSecondLevelCacheHitCount()).isEqualTo(2L);
        assertThat(stats.getSecondLevelCachePutCount()).isEqualTo(2L);
    }

    @Test
    public void testClearCache() {
        SessionFactory sessionFactory = em.getEntityManagerFactory().unwrap(SessionFactory.class);
        Statistics stats = sessionFactory.getStatistics();
        stats.setStatisticsEnabled(true);

        Customer customer = new Customer();
        runInTransaction(() -> em.persist(customer));
        assertThat(stats.getSecondLevelCachePutCount()).isEqualTo(0L);

        em.find(Customer.class, customer.getId());
        em.find(Customer.class, customer.getId());
        assertThat(stats.getSecondLevelCacheHitCount()).isEqualTo(1L);
        assertThat(stats.getSecondLevelCachePutCount()).isEqualTo(1L);

        sessionFactory.getCache().evictEntity(Customer.class, customer.getId());
        em.find(Customer.class, customer.getId());
        assertThat(stats.getSecondLevelCacheHitCount()).isEqualTo(1L);
        assertThat(stats.getSecondLevelCacheMissCount()).isEqualTo(2L);
        assertThat(stats.getSecondLevelCachePutCount()).isEqualTo(2L);
    }

    @Test
    public void testQueryCache() {
        inertProducts();

        runInTransaction(() -> {

            SessionFactory sessionFactory = em.getEntityManagerFactory().unwrap(SessionFactory.class);
            Statistics stats = sessionFactory.getStatistics();
            stats.setStatisticsEnabled(true);

            Session session = em.unwrap(Session.class);
            session.createQuery("FROM Product p WHERE p.onSale = true").
                    setCacheable(true).
                    list();

            assertThat(stats.getQueryCachePutCount()).isEqualTo(1L);
            assertThat(stats.getQueryCacheMissCount()).isEqualTo(1L);
            assertThat(stats.getSecondLevelCachePutCount()).isEqualTo(50L);
        });

        runInTransaction(() -> {

            SessionFactory sessionFactory = em.getEntityManagerFactory().unwrap(SessionFactory.class);
            Statistics stats = sessionFactory.getStatistics();
            stats.setStatisticsEnabled(true);

            Session session = em.unwrap(Session.class);
            session.createQuery("FROM Product p WHERE p.onSale = true").
                    setCacheable(true).
                    list();

            assertThat(stats.getQueryCachePutCount()).isEqualTo(1L);
            assertThat(stats.getQueryCacheMissCount()).isEqualTo(1L);
            assertThat(stats.getSecondLevelCachePutCount()).isEqualTo(50L);

            assertThat(stats.getQueryCacheHitCount()).isEqualTo(1L);
            assertThat(stats.getSecondLevelCacheHitCount()).isEqualTo(50L);
        });
    }

    @Test
    public void testQueryCacheClear() {
        inertProducts();

        runInTransaction(() -> {

            SessionFactory sessionFactory = em.getEntityManagerFactory().unwrap(SessionFactory.class);
            Statistics stats = sessionFactory.getStatistics();
            stats.setStatisticsEnabled(true);

            Session session = em.unwrap(Session.class);
            session.createQuery("FROM Product p WHERE p.onSale = true").
                    setCacheable(true).
                    setCacheRegion("onSaleProducts").
                    list();

            assertThat(stats.getQueryCachePutCount()).isEqualTo(1L);
            assertThat(stats.getQueryCacheMissCount()).isEqualTo(1L);
            assertThat(stats.getSecondLevelCachePutCount()).isEqualTo(50L);
        });

        runInTransaction(() -> {

            SessionFactory sessionFactory = em.getEntityManagerFactory().unwrap(SessionFactory.class);
            Statistics stats = sessionFactory.getStatistics();
            stats.setStatisticsEnabled(true);

            sessionFactory.getCache().evictQueryRegion("onSaleProducts");

            Session session = em.unwrap(Session.class);
            session.createQuery("FROM Product p WHERE p.onSale = true").
                    setCacheable(true).
                    setCacheRegion("onSaleProducts").
                    list();

            assertThat(stats.getQueryCachePutCount()).isEqualTo(2L);
            assertThat(stats.getQueryCacheMissCount()).isEqualTo(2L);
            assertThat(stats.getSecondLevelCachePutCount()).isEqualTo(50L);

            assertThat(stats.getQueryCacheHitCount()).isEqualTo(0L);
            assertThat(stats.getSecondLevelCacheHitCount()).isEqualTo(0L);
        });
    }

    private void inertProducts() {
        for (int i = 0; i < 100; i++) {
            int finalI = i;
            runInTransaction(() -> {
                Product p = new Product("P " + finalI, new Money(finalI + 100));
                if (finalI % 2 == 0)
                    p.putOnSale();
                em.persist(p);
            });
        }
    }

    private void runInTransaction(Runnable r) {
        tt.execute((c) -> {
            r.run();
            return null;
        });
    }

}
