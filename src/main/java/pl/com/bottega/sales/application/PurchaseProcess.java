package pl.com.bottega.sales.application;

public interface PurchaseProcess {

    Long createOrder(Long userId);

    void addProduct(Long orderId, Long productId, Integer count);

    void removeProduct(Long orderId, Long productId);

    void placeOrder(Long orderId) throws ProductNotAvailableException;

    void updateInventory(Long productId, Long count);

}
