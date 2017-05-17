package pl.com.bottega.sales.infrastructure.repositories;

import pl.com.bottega.common.infrastructure.repositories.JPABaseRepository;
import pl.com.bottega.sales.domain.Order;
import pl.com.bottega.sales.domain.repositories.OrderRepository;

public class JPAOrderRepository extends JPABaseRepository<Order>
        implements OrderRepository {

    public JPAOrderRepository() {
        super(Order.class);
    }
}
