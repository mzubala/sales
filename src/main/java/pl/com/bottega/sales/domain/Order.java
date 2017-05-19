package pl.com.bottega.sales.domain;

import pl.com.bottega.common.domain.BaseAggregateRoot;
import pl.com.bottega.common.domain.Money;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order extends BaseAggregateRoot {

    @ElementCollection(fetch = FetchType.EAGER)
    private List<OrderItem> items = new ArrayList<>();

    @Embedded
    private CustomerSnapshot customerSnapshot;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "value", column = @Column(name = "total_value")),
            @AttributeOverride(name = "currencyCode", column = @Column(name = "total_currency"))
    })
    private Money total;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "value", column = @Column(name = "rabate_value")),
            @AttributeOverride(name = "currencyCode", column = @Column(name = "rabate_currency"))
    })
    private Money rabate;

    @Enumerated(value = EnumType.STRING)
    private OrderStatus orderStatus;

    public Order(CustomerSnapshot customer) {
        this.total = Money.ZERO;
        this.customerSnapshot = customer;
    }

    public void addItem(ProductSnapshot product, int count) {

    }

    public void removeItem(Long productId) {

    }

    public void place() {

    }

}
