package pl.com.bottega.sales.domain;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;

@Embeddable
public class OrderItem {

    @Embedded
    private ProductSnapshot productSnapshot;
    private Integer quantity;

    OrderItem() {
    }

    public OrderItem(ProductSnapshot productSnapshot, Integer quantity) {
        this.productSnapshot = productSnapshot;
        this.quantity = quantity;
    }
}
