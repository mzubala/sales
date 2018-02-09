package pl.com.bottega.sales.domain;

import pl.com.bottega.common.domain.BaseAggregateRoot;
import pl.com.bottega.common.domain.Money;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "orders")
public class Order extends BaseAggregateRoot {

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name="order_id")
    private List<OrderItem> items = new ArrayList<>();

    @ManyToOne
    private Customer customer;

    private Money total;

    private OrderStatus status;

    private Order() {}

    public Order(Customer customer) {
        this.total = Money.ZERO;
        this.status = OrderStatus.NEW;
        this.customer = customer;
    }

    public void addItem(Product product, int count) {
        validateNewOrder();
        for(int i = 0; i<count; i++)
            items.add(new OrderItem(product, customer));
    }

    private void validateNewOrder() {
        if(status != OrderStatus.NEW)
            throw new IllegalStateException();
    }

    public void removeItem(Product product) {
        validateNewOrder();
        items.removeIf((item) -> item.containsProduct(product));
    }

    public void submit() {
        validateNewOrder();
        status = OrderStatus.PLACED;
    }
}
