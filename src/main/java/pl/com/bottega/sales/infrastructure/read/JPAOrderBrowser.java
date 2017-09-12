package pl.com.bottega.sales.infrastructure.read;

import org.springframework.transaction.annotation.Transactional;
import pl.com.bottega.common.domain.Money;
import pl.com.bottega.sales.domain.Order;
import pl.com.bottega.sales.domain.OrderBuilder;
import pl.com.bottega.sales.read.OrderBrowser;
import pl.com.bottega.sales.read.OrderDto;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class JPAOrderBrowser implements OrderBrowser {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<OrderDto> browse(Long customerId) {

        return null;
    }

    @Override
    @Transactional
    public byte[] exportOrders(Long customerId) {
        List<Order> orders = entityManager.createQuery("FROM Order o LEFT JOIN FETCH o.orderItems").getResultList();
        OrdersXmlBuilder builder = new OrdersXmlBuilder();
        for(Order order : orders) {
            order.export(builder);
        }
        return builder.getProduct();
    }

    class OrdersXmlBuilder implements OrderBuilder {

        @Override
        public void addStatus(String status) {

        }

        @Override
        public void addTotal(Money money) {

        }

        public byte[] getProduct() {
            return null;
        }

    }

}
