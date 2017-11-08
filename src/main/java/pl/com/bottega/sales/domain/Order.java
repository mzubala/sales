package pl.com.bottega.sales.domain;

import pl.com.bottega.common.domain.BaseAggregateRoot;
import pl.com.bottega.common.domain.Money;

import javax.persistence.*;
import java.util.*;

public class Order extends BaseAggregateRoot {

    private List<OrderItem> items = new ArrayList<>();

    private Money total;

    private OrderStatus status;

    public Order(Customer customer) {
        this.total = Money.ZERO;
        this.status = OrderStatus.NEW;
    }

    public void addItem(Product product, int count) {

    }

    public void removeItem(Product product) {

    }

}
