package pl.com.bottega.sales.application;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.com.bottega.sales.domain.repositories.CustomerRepository;
import pl.com.bottega.sales.domain.repositories.OrderRepository;
import pl.com.bottega.sales.domain.repositories.ProductRepository;

@Component
@Transactional
public class StandardPurchaseProcess implements PurchaseProcess {

    private OrderRepository orderRepository;
    private ProductRepository productRepository;
    private CustomerRepository customerRepository;

    public StandardPurchaseProcess(OrderRepository orderRepository, ProductRepository productRepository, CustomerRepository customerRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public Long createOrder(Long customerId) {
        return null;
    }

    @Override
    public void addProduct(Long orderId, Long productId, Integer count) {

    }

    @Override
    public void removeProduct(Long orderId, Long productId) {

    }

    @Override
    public void placeOrder(Long orderId) {

    }
}
