package pl.com.bottega.sales.domain;

import pl.com.bottega.common.domain.BaseAggregateRoot;
import pl.com.bottega.common.domain.Money;

import javax.persistence.Embedded;
import javax.persistence.Entity;

@Entity
public class Product extends BaseAggregateRoot {

    private String name;

    @Embedded
    private Money price;

    Product() {}

    public Product(String name, Money price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public Money getPrice(Customer customer) {
        return price;
    }

    public ProductData getSnapshot() {
        return new ProductData(getId(), name, price);
    }
}
