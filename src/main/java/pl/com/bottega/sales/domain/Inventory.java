package pl.com.bottega.sales.domain;

import pl.com.bottega.common.domain.BaseAggregateRoot;
import pl.com.bottega.sales.application.ProductNotAvailableException;

import javax.persistence.Entity;

@Entity
public class Inventory extends BaseAggregateRoot {

    private Long productId;
    private Long productCount = 0L;

    Inventory() {
    }

    public Inventory(Long productId) {
        this.productId = productId;
    }

    public void update(Long newCount) {
        this.productCount = newCount;
    }

    public void sell(int count) {
        if(count > productCount)
            throw new ProductNotAvailableException();
        productCount -= count;
    }
}
