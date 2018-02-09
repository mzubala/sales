package pl.com.bottega.sales.domain;

import pl.com.bottega.common.domain.Money;

import javax.persistence.Embeddable;

@Embeddable
public class ProductSnapshot {

  private Long productId;

  private Money price;

  ProductSnapshot() {}

  public ProductSnapshot(Long productId, Money price) {
    this.productId = productId;
    this.price = price;
  }
}
