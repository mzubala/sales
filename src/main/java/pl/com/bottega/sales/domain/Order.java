package pl.com.bottega.sales.domain;

import pl.com.bottega.common.domain.BaseAggregateRoot;
import pl.com.bottega.common.domain.Money;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "orders")
public class Order extends BaseAggregateRoot {

    @OneToMany
    private List<OrderLine> items = new ArrayList<>();

    @Embedded
    private Money total;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @Embedded
    private CustomerData customerData;

    Order() {}

    public Order(CustomerData customerData) {
        this.total = Money.ZERO;
        this.status = OrderStatus.NEW;
        this.customerData = customerData;
    }

    public void addItem(ProductData product, int count) {
        Optional<OrderLine> itemOptional = findByProductId(product.getProductId());
        if(itemOptional.isPresent())
            itemOptional.get().increaseCount(count);
        else {
            OrderLine newItem = new OrderLine(product, count);
            items.add(newItem);
        }
        updateTotal();
    }

    private Optional<OrderLine> findByProductId(Long productId) {
        return items.stream().filter(item -> item.hasProduct(productId)).findFirst();
    }

    public void removeItem(Long productId) {
        items.removeIf(item -> item.hasProduct(productId));
        updateTotal();
    }

    private void updateTotal() {
        Money total = Money.ZERO;
        for(OrderLine line : items)
            total = total.add(line.getPrice());
        this.total = total;
    }

    public void submit() {
        if(status == OrderStatus.PLACED)
            throw new IllegalStateException("Order already submitted");
        status = OrderStatus.PLACED;
    }

}
