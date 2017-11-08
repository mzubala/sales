package pl.com.bottega.sales.domain;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import pl.com.bottega.common.domain.BaseAggregateRoot;
import pl.com.bottega.common.domain.Money;
import pl.com.bottega.common.domain.events.EventPublisher;
import pl.com.bottega.common.domain.events.EventPublisherAware;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

import static com.google.common.base.Preconditions.checkState;
import static sun.java2d.cmm.kcms.CMM.checkStatus;

@Entity
@Table(name="orders")
@DynamicUpdate
public class Order extends BaseAggregateRoot implements EventPublisherAware {

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @Fetch(FetchMode.JOIN)
    @JoinColumn
    @OrderColumn
    private List<OrderItem> items = new ArrayList<>();

    @Embedded
    private Money total;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @Embedded
    private CustomerSnapshot customer;

    @Transient
    private EventPublisher eventPublisher;
    private LocalDate placedAt;

    Order() {}

    public Order(CustomerSnapshot customer, EventPublisher eventPublisher) {
        this.customer = customer;
        this.total = Money.ZERO;
        this.status = OrderStatus.NEW;
        this.eventPublisher = eventPublisher;
    }

    public void addItem(ProductSnapshot product, int count) {
        if(status == OrderStatus.PLACED)
            throw new IllegalStateException("Order already placed");
        if(count <= 0)
            throw new IllegalArgumentException("Count must be positive");
        OrderItem item = findByProduct(product).orElseGet(() -> {
            OrderItem newItem = new OrderItem(product);
            items.add(newItem);
            return newItem;
        });
        item.inc(count);
    }

    private Optional<OrderItem> findByProduct(ProductSnapshot product) {
        return items.stream().filter((oi) -> oi.isForProduct(product)).findFirst();
    }

    public void removeItem(ProductSnapshot product) {
        Optional<OrderItem> orderItemOptional = findByProduct(product);
        OrderItem orderItem = orderItemOptional.orElseThrow(() -> new IllegalArgumentException("No such product"));
        items.remove(orderItem);
    }

    public void export(OrderBuilder orderBuilder) {
        orderBuilder.addStatus(status.toString());
        orderBuilder.addTotal(total);
    }

    public Long getCustomerId() {
        return customer.getId();
    }

    public void place() {
        checkState(status == OrderStatus.NEW);
        status = OrderStatus.PLACED;
        placedAt = LocalDate.now();
    }

    @Override
    public void setEventPublisher(EventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

}
