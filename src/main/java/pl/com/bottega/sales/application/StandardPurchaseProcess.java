package pl.com.bottega.sales.application;

import pl.com.bottega.sales.domain.repositories.OrderRepository;

public class StandardPurchaseProcess implements PurchaseProcess {

    private OrderRepository orderRepository;

    public StandardPurchaseProcess(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
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
