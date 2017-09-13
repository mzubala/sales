package pl.com.bottega.sales.application;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.com.bottega.common.domain.events.EventPublisher;
import pl.com.bottega.sales.domain.*;
import pl.com.bottega.sales.domain.repositories.CustomerRepository;
import pl.com.bottega.sales.domain.repositories.OrderRepository;
import pl.com.bottega.sales.domain.repositories.ProductRepository;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;

@Component
@Transactional
public class StandardPurchaseProcess implements PurchaseProcess {

    private OrderRepository orderRepository;
    private ProductRepository productRepository;
    private CustomerRepository customerRepository;
    private EventPublisher eventPublisher;
    private InventoryRepository inventoryRepository;

    public StandardPurchaseProcess(OrderRepository orderRepository, ProductRepository productRepository,
                                   CustomerRepository customerRepository, EventPublisher eventPublisher) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public Long createOrder(Long customerId) {
        Customer customer = customerRepository.get(customerId);
        Order order = new Order(customer.getSnapshot(), eventPublisher);
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
        Collection<OrderItem> orderedProducts = order.getItems(); //FIXME
        orderedProducts.forEach(i -> {
            Inventory inventory = inventoryRepository.loadForProduct(i.getProductId()).get();
            inventory.sell(i.getProductCount());
            inventoryRepository.put(inventory);
            // TODO
        });

        orderRepository.put(order);
    }

    @Override
    public void updateInventory(Long productId, Long count) {
        Optional<Inventory> opt = inventoryRepository.loadForProduct(productId);
        Inventory inventory = opt.orElse(new Inventory(productId));
        inventory.update(count);
        inventoryRepository.put(inventory);
    }
}
