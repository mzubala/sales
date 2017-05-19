package pl.com.bottega.sales.domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;

@Embeddable
@Access(value = AccessType.FIELD)
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
