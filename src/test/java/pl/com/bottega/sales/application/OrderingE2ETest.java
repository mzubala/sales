package pl.com.bottega.sales.application;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.support.TransactionTemplate;
import pl.com.bottega.common.domain.Money;
import pl.com.bottega.sales.domain.Customer;
import pl.com.bottega.sales.domain.Order;
import pl.com.bottega.sales.domain.Product;
import pl.com.bottega.sales.read.OrderBrowser;
import pl.com.bottega.sales.read.OrderDto;
import pl.com.bottega.sales.read.SearchResults;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@RunWith(SpringRunner.class)
public class OrderingE2ETest {

    @Autowired
    private PurchaseProcess purchaseProcess;

    @Autowired
    private OrderBrowser orderBrowser;

    @Autowired
    private TransactionTemplate tt;

    @PersistenceContext
    private EntityManager em;

    @Test
    public void createsOrder() {
        Customer c = new Customer();
        runInTransaction(() -> em.persist(c));
        purchaseProcess.createOrder(c.getId());
        SearchResults<OrderDto> searchResults = orderBrowser.browse(c.getId(), 1, 20);
        List<OrderDto> orderDtos = searchResults.getPage();
        assertThat(orderDtos.size()).isEqualTo(1);
        assertThat(searchResults.getPagesCount()).isEqualTo(1);
    }

    @Test
    public void addProducts() {
        Customer c = new Customer();
        runInTransaction(() -> em.persist(c));
        inertProducts();
        Long orderId = purchaseProcess.createOrder(c.getId());
        Order o = em.find(Order.class, orderId);
        Long v = o.getVersion();
        purchaseProcess.addProduct(orderId, 10L, 15);
        purchaseProcess.addProduct(orderId, 20L, 10);
        purchaseProcess.addProduct(orderId, 30L, 5);
        o = em.find(Order.class, orderId);
        assertThat(o.getVersion()).isEqualTo(v+6);

    }

    private void runInTransaction(Runnable r) {
        tt.execute((c) -> {
            r.run();
            return null;
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

}
