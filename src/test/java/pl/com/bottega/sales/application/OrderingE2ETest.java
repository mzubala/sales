package pl.com.bottega.sales.application;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.support.TransactionTemplate;
import pl.com.bottega.sales.domain.Customer;
import pl.com.bottega.sales.read.OrderBrowser;
import pl.com.bottega.sales.read.OrderDto;

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
        List<OrderDto> orderDtos = orderBrowser.browse(c.getId());
        assertThat(orderDtos.size()).isEqualTo(1);
    }

    private void runInTransaction(Runnable r) {
        tt.execute((c) -> {
            r.run();
            return null;
        });
    }

}
