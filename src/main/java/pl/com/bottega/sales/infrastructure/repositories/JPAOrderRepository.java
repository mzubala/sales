package pl.com.bottega.sales.infrastructure.repositories;

import org.springframework.stereotype.Component;
import pl.com.bottega.common.infrastructure.repositories.JPABaseRepository;
import pl.com.bottega.sales.domain.Order;
import pl.com.bottega.sales.domain.repositories.OrderRepository;

@Component
public class JPAOrderRepository extends JPABaseRepository<Order>
        implements OrderRepository {

}
