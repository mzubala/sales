package pl.com.bottega.common.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;

@Entity
public class Address2 extends BaseEntity {

  @OneToOne(mappedBy = "address", fetch = FetchType.LAZY)
  public Auction auction;

}
