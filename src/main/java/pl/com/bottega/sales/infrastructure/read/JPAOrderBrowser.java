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
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

public class JPAOrderBrowser implements OrderBrowser {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public SearchResults<OrderDto> browse(Long customerId, int pageNumber, int pageSize) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery criteriaQuery = criteriaBuilder.createQuery();
        Root order = criteriaQuery.from(Order.class);
        criteriaQuery.select(criteriaBuilder.construct(OrderDto.class, order.get("placedAt"), order.get("total")));
        criteriaQuery.orderBy(criteriaBuilder.desc(order.get("id")));
        prepareQuery(customerId, criteriaBuilder, criteriaQuery, order);

        CriteriaQuery countQuery = criteriaBuilder.createQuery();
        Root countRoot = countQuery.from(Order.class);
        countQuery.select(criteriaBuilder.count(countRoot));
        prepareQuery(customerId, criteriaBuilder, countQuery, countRoot);

        Query query = entityManager.createQuery(criteriaQuery);
        List<OrderDto> orderDtos = query.
                setFirstResult((pageNumber - 1) * pageSize).
                setMaxResults(pageSize).
                getResultList();

        long total = (long) entityManager.createQuery(countQuery).getSingleResult();

        return new SearchResults<OrderDto>(
                orderDtos,
                (int) (total / pageSize + (total % pageSize > 0 ? 1 : 0)),
                pageNumber, pageSize, total);
    }

    private void prepareQuery(Long customerId, CriteriaBuilder criteriaBuilder, CriteriaQuery criteriaQuery, Root root) {
        Predicate predicate = criteriaBuilder.equal(root.get("customer").get("customerId"), customerId);
        criteriaQuery.where(predicate);
    }

    @Override
    @Transactional
    public byte[] exportOrders(Long customerId) {
        List<Order> orders = entityManager.createQuery("FROM Order o LEFT JOIN FETCH o.orderItems").getResultList();
        OrdersXmlBuilder builder = new OrdersXmlBuilder();
        for (Order order : orders) {
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
