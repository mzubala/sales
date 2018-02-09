package pl.com.bottega.common.domain;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.stat.Statistics;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.support.TransactionTemplate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import static org.assertj.core.api.Java6Assertions.assertThat;

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

  @Test(expected = PersistenceException.class)
  public void showBagFetchProblem() {


    tt.execute((c) -> {
      entityManager.persist(new Auction());
      return null;
    });

    entityManager.createQuery("SELECT a FROM Auction a " +
        "JOIN FETCH a.bids " +
        "JOIN FETCH a.addresses").getResultList();
  }

  @Test
  public void np1Test() {
    int n = 100;
    for(int i = 0; i<n; i++) {
      createAuction();
    }


    tt.execute((c) -> {
      SessionFactory sessionFactory = entityManager.unwrap(Session.class).getSessionFactory();
      Statistics stats = sessionFactory.getStatistics();
      stats.setStatisticsEnabled(true);
      List<Auction> auctions = entityManager.createQuery("SELECT a FROM Auction a LEFT JOIN a.bids ").getResultList();
      for(Auction auction : auctions)
        auction.bids.size();
      long statementsCount = stats.getPrepareStatementCount();
      assertThat(statementsCount).isEqualTo(n + 1);
      return null;
    });
  }

  private void createAuction() {
    tt.execute((c) -> {
      Auction auction = new Auction();
      //Bid bid = new Bid();
      //auction.bids.add(bid);
      //entityManager.persist(bid);
      entityManager.persist(auction);
      return null;
    });
  }

}
