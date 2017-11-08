package pl.com.bottega.sales.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import pl.com.bottega.common.domain.BaseAggregateRoot;
import pl.com.bottega.common.domain.Money;

import javax.persistence.Embedded;
import javax.persistence.Entity;

@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
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

    public ProductSnapshot getSnapshot(Customer customer) {
        return new ProductSnapshot(getId(), name, getPrice(customer));
    }
}
