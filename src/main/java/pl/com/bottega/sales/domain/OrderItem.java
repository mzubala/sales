package pl.com.bottega.sales.domain;

import pl.com.bottega.common.domain.BaseEntity;

import javax.persistence.Embedded;
import javax.persistence.Entity;

@Entity
public class OrderItem extends BaseEntity {

    @Embedded
    private ProductSnapshot snapshot;

    private int count;

    OrderItem() {
    }

    public OrderItem(ProductSnapshot snapshot) {
        this.snapshot = snapshot;
    }

    public void inc(int count) {
        this.count += count;
    }

    public boolean isForProduct(ProductSnapshot product) {
        return snapshot.isForSameProduct(product);
    }
}
