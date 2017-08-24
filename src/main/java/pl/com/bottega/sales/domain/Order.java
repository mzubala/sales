package pl.com.bottega.sales.domain;

import pl.com.bottega.common.domain.BaseAggregateRoot;
import pl.com.bottega.common.domain.Money;

import javax.persistence.*;
import java.util.*;

@Entity
public class Order extends BaseAggregateRoot {

    private List<OrderLine> items = new ArrayList<>();

    private Money total;

    private OrderStatus status;

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

    }

    private Optional<OrderLine> findByProductId(Long productId) {
        return items.stream().filter(item -> item.hasProduct(productId)).findFirst();
    }

    public void removeItem(Long productId) {
        items.removeIf(item -> item.hasProduct(productId));
    }

    public void submit() {

    }

}
