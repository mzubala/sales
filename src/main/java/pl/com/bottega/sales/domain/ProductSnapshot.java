package pl.com.bottega.sales.domain;

import pl.com.bottega.common.domain.Money;

import javax.persistence.Embeddable;

@Embeddable
public class ProductSnapshot {

    private String name;
    private Money price;
    private Long productId;

    ProductSnapshot() {
    }

    public ProductSnapshot(Long productId, String name, Money price) {
        this.productId = productId;
        this.name = name;
        this.price = price;
    }


    public boolean isForSameProduct(ProductSnapshot product) {
        return product.productId.equals(this.productId);
    }

    public Long getProductId() {
        return productId;
    }
}
