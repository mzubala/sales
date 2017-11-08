package pl.com.bottega.sales.application;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.com.bottega.sales.domain.Customer;
import pl.com.bottega.sales.domain.Order;
import pl.com.bottega.sales.domain.Product;
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
        Customer customer = customerRepository.get(customerId);
        Order order = new Order(customer.getSnapshot());
        orderRepository.put(order);
        return order.getId();
    }

    @Override
    public void addProduct(Long orderId, Long productId, Integer count) {
        Order order = orderRepository.get(orderId);
        Customer customer = customerRepository.get(order.getCustomerId());
        Product product = productRepository.get(productId);

        order.addItem(product.getSnapshot(customer), count);

        orderRepository.put(order);
    }

    @Override
    public void removeProduct(Long orderId, Long productId) {
        Order order = orderRepository.get(orderId);
        Customer customer = customerRepository.get(order.getCustomerId());
        Product product = productRepository.get(productId);

        order.removeItem(product.getSnapshot(customer));

        orderRepository.put(order);
    }

    @Override
    public void placeOrder(Long orderId) {
        Order order = orderRepository.get(orderId);

        order.place();

        orderRepository.put(order);
    }
}
