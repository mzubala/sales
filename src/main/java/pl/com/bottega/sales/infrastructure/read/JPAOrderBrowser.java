package pl.com.bottega.sales.infrastructure.read;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.com.bottega.common.domain.Money;
import pl.com.bottega.sales.domain.Order;
import pl.com.bottega.sales.domain.OrderBuilder;
import pl.com.bottega.sales.read.OrderBrowser;
import pl.com.bottega.sales.read.OrderDto;
import pl.com.bottega.sales.read.SearchResults;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Component
public class JPAOrderBrowser implements OrderBrowser {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public SearchResults<OrderDto> browse(Long customerId, int pageNumber, int pageSize) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery criteriaQuery = criteriaBuilder.createQuery();
        Root order = criteriaQuery.from(Order.class);
        criteriaQuery.select(criteriaBuilder.construct(OrderDto.class, order.get("placedAt"), order.get("total")));
        criteriaQuery.where(criteriaBuilder.equal(order.get("customer").get("customerId"), customerId));
        Query query = entityManager.createQuery(criteriaQuery);
        List<OrderDto> orderDtos = query.getResultList();
        return new SearchResults<>(orderDtos, 0, 0, 0, 0);
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
