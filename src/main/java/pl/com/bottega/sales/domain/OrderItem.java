package pl.com.bottega.sales.domain;

import pl.com.bottega.common.domain.BaseEntity;

import javax.persistence.Embedded;
import javax.persistence.Entity;

@Entity
public class OrderItem extends BaseEntity {

  @Embedded
  private ProductSnapshot product;

  private OrderItem() {

  }

  public OrderItem(Product product, Customer customer) {
    this.product = product.getSnaphost(customer);
  }

  public boolean containsProduct(Product product) {
    return false;
  }
}
