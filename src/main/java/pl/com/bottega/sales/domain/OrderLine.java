package pl.com.bottega.sales.domain;

import pl.com.bottega.common.domain.BaseEntity;
import pl.com.bottega.common.domain.Money;

import javax.persistence.Embedded;
import javax.persistence.Entity;

@Entity
public class OrderLine extends BaseEntity {

    @Embedded
    private ProductData productData;
    private Integer count;

    OrderLine() {}

    public OrderLine(ProductData productData, Integer count) {
        this.productData = productData;
        this.count = count;
    }

    public void increaseCount(int count) {
        this.count += count;
    }

    public boolean hasProduct(Long productId) {
        return productData.getProductId().equals(productId);
    }

    public Money getPrice() {
        return productData.getPrice().multiplyBy(count);
    }
}
