package pl.com.bottega.sales.application;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.com.bottega.sales.domain.*;
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
        CustomerData customerData = customer.getSnapshot();

        Order order = new Order(customerData);

        orderRepository.put(order);
        return order.getId();
    }

    @Override
    public void addProduct(Long orderId, Long productId, Integer count) {
        Product product = productRepository.get(productId);
        Order order = orderRepository.get(orderId);
        ProductData productData = product.getSnapshot();

        order.addItem(productData, count);

        orderRepository.put(order);
    }

    @Override
    public void removeProduct(Long orderId, Long productId) {
        Order order = orderRepository.get(orderId);

        order.removeItem(productId);

        orderRepository.put(order);
    }

    @Override
    public void placeOrder(Long orderId) {
        Order order = orderRepository.get(orderId);

        order.submit();


        orderRepository.put(order);
    }
}
