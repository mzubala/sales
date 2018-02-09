package pl.com.bottega.common.domain;

import org.hibernate.Session;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.support.TransactionTemplate;

import javax.persistence.EntityManager;

@SpringBootTest
@RunWith(SpringRunner.class)
public class JpaPlay {

  @Autowired
  private EntityManager entityManager;

  @Autowired
  private TransactionTemplate tt;

  @Test
  public void showLazyLoadingException() {
    tt.execute((c) -> {
      entityManager.persist(new Auction());
      return null;
    });

    Auction auction = entityManager.find(Auction.class, 1L);
    auction.bids.size();
  }

  @Test
  public void showNoLLOnOneToOne() {
    tt.execute((c) -> {
      Auction auction = new Auction();
      Address2 address = new Address2();
      auction.address = address;
      address.auction = auction;
      entityManager.persist(address);
      entityManager.persist(auction);
      return null;
    });

    Address2 address = entityManager.find(Address2.class, 1L);
  }

  @Test
  public void showBagFetchProblem() {


    tt.execute((c) -> {
      entityManager.persist(new Auction());
      return null;
    });

    entityManager.createQuery("SELECT a FROM Auction a " +
        "JOIN FETCH a.bids " +
        "JOIN FETCH a.addresses").getResultList();
  }

}
