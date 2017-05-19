package pl.com.bottega.mappingplay;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import javax.persistence.*;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BagTest {

    @Autowired
    private PlatformTransactionManager mgr;

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    public void testBag() {
        TransactionTemplate tmpl = new TransactionTemplate(mgr);
        Auction a = tmpl.execute((callback) -> {
            Auction auction = new Auction();
            auction.add(new Bid());
            entityManager.persist(auction);
            return auction;
        });

        tmpl.execute((callback) -> {
            Auction aa = entityManager.find(Auction.class, a.getId());
            aa.add(new Bid());
            return aa;
        });

        tmpl.execute((c) -> {
            Auction aa = entityManager.find(Auction.class, a.getId());
            return aa;
        });

        tmpl.execute((c) -> {
            Auction aa = entityManager.find(Auction.class, a.getId());
            return aa;
        });
    }

    @Test
    public void nPlus1Test() {
        TransactionTemplate tmpl = new TransactionTemplate(mgr);
        Auction a = tmpl.execute((callback) -> {
            Auction auction = new Auction();
            auction.add(new Bid());
            auction.add(new Bid());
            auction.add(new Bid());
            auction.add(new Photo());
            auction.add(new Photo());
            auction.add(new Photo());
            entityManager.persist(auction);
            return auction;
        });


        String s = "SELECT DISTINCT(a) FROM Auction a LEFT JOIN FETCH a.bids LEFT JOIN FETCH a.photos";

        List<Auction> auctions = entityManager.createQuery(s).getResultList();
        System.out.println("auctions.size = " + auctions.size());
    }

}
