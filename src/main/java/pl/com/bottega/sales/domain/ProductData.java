package pl.com.bottega.sales.domain;

import pl.com.bottega.common.domain.Money;

public class ProductData {

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
}
