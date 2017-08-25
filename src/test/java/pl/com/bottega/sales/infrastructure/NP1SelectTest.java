package pl.com.bottega.sales.infrastructure;

import org.hibernate.Session;
import org.hibernate.stat.SessionStatistics;
import org.hibernate.stat.Statistics;
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
import pl.com.bottega.sales.domain.OrderExporter;
import pl.com.bottega.sales.domain.ProductData;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Table;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NP1SelectTest {

    @PersistenceContext
    private EntityManager em;

    @Test
    @Transactional
    public void testNp1Select() {
        for(int i = 0; i<500; i++) {
            Address address = new Address("Fabryczna 10", "", "20-100", "Wrocław", "DW", "PL");
            Order order = new Order(new CustomerData("Jan", "Nowak", address, address));
            order.addItem(new ProductData(1L, "Kalesony", new Money(50.0)), 5);
            em.persist(order);
        }
        em.flush();
        em.clear();

        //Statistics ss = em.unwrap(Session.class).getSessionFactory().getStatistics();
        List<Order> orders = em.createQuery("SELECT o FROM Order o " +
                "LEFT JOIN FETCH o.customerData.shippingAddress " +
                "LEFT JOIN FETCH o.customerData.billingAddress " +
                "LEFT JOIN FETCH o.items").getResultList();
        for(Order order : orders) {
            order.export(new OrderExporter() {
                @Override
                public void addLines(Map<String, Integer> lines) {
                    System.out.println(lines);
                }

                @Override
                public void addTotal(Money total) {
                    System.out.println(total);
                }
            });
        }
        //assertThat(ss.getPrepareStatementCount()).isEqualTo(501);

    }

}
