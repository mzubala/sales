package pl.com.bottega.sales.domain;

import pl.com.bottega.common.domain.Money;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;

@Embeddable
public class ProductData {

    @Embedded
    private Money price;
    private String name;
    private Long productId;

    ProductData() {}

    public ProductData(Long productId, String name, Money price) {
        this.price = price;
        this.name = name;
        this.productId = productId;
    }

    public Long getProductId() {
        return productId;
    }

    public Money getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }
}
