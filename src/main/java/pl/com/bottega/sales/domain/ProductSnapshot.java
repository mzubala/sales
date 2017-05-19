package pl.com.bottega.sales.domain;

import pl.com.bottega.common.domain.Money;

import javax.persistence.*;

@Embeddable
@Access(value = AccessType.FIELD)
public class ProductSnapshot {

    private Long productId;
    private String name;

    @Embedded
    private Money price;

    ProductSnapshot() {
    }

    public ProductSnapshot(Long productId, String name, Money price) {
        this.productId = productId;
        this.name = name;
        this.price = price;
    }

    public Long getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public Money getPrice() {
        return price;
    }
}
