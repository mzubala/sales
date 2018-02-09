package pl.com.bottega.common.domain;

import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import java.util.LinkedList;

import java.util.List;

@Entity
public class Auction extends BaseEntity {

  @OneToMany
  @JoinColumn
  public List<Bid> bids = new LinkedList<>();

  @OneToMany
  @JoinColumn
  public List<Address2> addresses = new LinkedList<>();

  @OneToOne(fetch = FetchType.LAZY)
  public Address2 address;

}
