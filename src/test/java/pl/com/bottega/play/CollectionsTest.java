package pl.com.bottega.play;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.support.TransactionTemplate;
import pl.com.bottega.sales.application.PurchaseProcess;
import pl.com.bottega.sales.domain.Customer;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CollectionsTest {

    @Autowired
    private TransactionTemplate tt;

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private PurchaseProcess purchaseProcess;

    @Test
    public void testBag() {
        Auction auction = new Auction();
        runInTransaction(() -> em.persist(auction));

        System.out.println("=========== e1");
        runInTransaction(() -> {
            Auction fetched = em.find(Auction.class, auction.getId());
            fetched.bag.add("e1");
        });

        System.out.println("=========== e2");
        runInTransaction(() -> {
            Auction fetched = em.find(Auction.class, auction.getId());
            fetched.bag.add("e2");
        });

        System.out.println("=========== e3");
        runInTransaction(() -> {
            Auction fetched = em.find(Auction.class, auction.getId());
            fetched.bag.add("e3");
        });
    }

    @Test
    public void testList() {
        Auction auction = new Auction();
        runInTransaction(() -> em.persist(auction));

        System.out.println("=========== e1");
        runInTransaction(() -> {
            Auction fetched = em.find(Auction.class, auction.getId());
            fetched.list.add("e1");
        });

        System.out.println("=========== e2");
        runInTransaction(() -> {
            Auction fetched = em.find(Auction.class, auction.getId());
            fetched.list.add("e2");
        });

        System.out.println("=========== e3");
        runInTransaction(() -> {
            Auction fetched = em.find(Auction.class, auction.getId());
            fetched.list.add("e3");
        });

    }

    @Test
    public void testSet() {
        Auction auction = new Auction();
        runInTransaction(() -> em.persist(auction));

        System.out.println("=========== e1");
        runInTransaction(() -> {
            Auction fetched = em.find(Auction.class, auction.getId());
            fetched.set.add("e1");
        });

        System.out.println("=========== e2");
        runInTransaction(() -> {
            Auction fetched = em.find(Auction.class, auction.getId());
            fetched.set.add("e2");
        });

        System.out.println("=========== e3");
        runInTransaction(() -> {
            Auction fetched = em.find(Auction.class, auction.getId());
            fetched.set.add("e3");
        });

    }

    @Test
    public void testEntityBagLink() {
        Auction auction = new Auction();
        runInTransaction(() -> em.persist(auction));

        System.out.println("=========== e1");
        runInTransaction(() -> {
            Auction fetched = em.find(Auction.class, auction.getId());
            fetched.entityBagLink.add(new Bid());
        });

        System.out.println("=========== e2");
        runInTransaction(() -> {
            Auction fetched = em.find(Auction.class, auction.getId());
            fetched.entityBagLink.add(new Bid());
        });

        System.out.println("=========== e3");
        runInTransaction(() -> {
            Auction fetched = em.find(Auction.class, auction.getId());
            fetched.entityBagLink.add(new Bid());
        });
    }

    @Test
    public void testEntityListLinked() {
        Auction auction = new Auction();
        runInTransaction(() -> em.persist(auction));

        System.out.println("=========== e1");
        runInTransaction(() -> {
            Auction fetched = em.find(Auction.class, auction.getId());
            fetched.entityListLink.add(new Bid());
        });

        System.out.println("=========== e2");
        runInTransaction(() -> {
            Auction fetched = em.find(Auction.class, auction.getId());
            fetched.entityListLink.add(new Bid());
        });

        System.out.println("=========== e3");
        runInTransaction(() -> {
            Auction fetched = em.find(Auction.class, auction.getId());
            fetched.entityListLink.add(new Bid());
        });

    }

    @Test
    public void testEntitySetLinked() {
        Auction auction = new Auction();
        runInTransaction(() -> em.persist(auction));

        System.out.println("=========== e1");
        runInTransaction(() -> {
            Auction fetched = em.find(Auction.class, auction.getId());
            fetched.entitySetLink.add(new Bid());
        });

        System.out.println("=========== e2");
        runInTransaction(() -> {
            Auction fetched = em.find(Auction.class, auction.getId());
            fetched.entitySetLink.add(new Bid());
        });

        System.out.println("=========== e3");
        runInTransaction(() -> {
            Auction fetched = em.find(Auction.class, auction.getId());
            fetched.entitySetLink.add(new Bid());
        });

    }

    @Test
    public void testEntityBagJoin() {


        Auction auction = new Auction();
        runInTransaction(() -> em.persist(auction));

        System.out.println("=========== e1");
        runInTransaction(() -> {
            Auction fetched = em.find(Auction.class, auction.getId());
            fetched.entityBagJoin.add(new Bid());
        });

        System.out.println("=========== e2");
        runInTransaction(() -> {
            Auction fetched = em.find(Auction.class, auction.getId());
            fetched.entityBagJoin.add(new Bid());
        });

        System.out.println("=========== e3");
        runInTransaction(() -> {
            Auction fetched = em.find(Auction.class, auction.getId());
            fetched.entityBagJoin.add(new Bid());
        });
    }

    @Test
    public void testEntityListJoined() {
        Auction auction = new Auction();
        runInTransaction(() -> em.persist(auction));

        System.out.println("=========== e1");
        runInTransaction(() -> {
            Auction fetched = em.find(Auction.class, auction.getId());
            fetched.entityListJoin.add(new Bid());
        });

        System.out.println("=========== e2");
        runInTransaction(() -> {
            Auction fetched = em.find(Auction.class, auction.getId());
            fetched.entityListJoin.add(new Bid());
        });

        System.out.println("=========== e3");
        runInTransaction(() -> {
            Auction fetched = em.find(Auction.class, auction.getId());
            fetched.entityListJoin.add(new Bid());
        });

    }

    @Test
    public void testEntitySetJoined() {
        Auction auction = new Auction();
        runInTransaction(() -> em.persist(auction));

        System.out.println("=========== e1");
        runInTransaction(() -> {
            Auction fetched = em.find(Auction.class, auction.getId());
            fetched.entitySetJoin.add(new Bid());
        });

        System.out.println("=========== e2");
        runInTransaction(() -> {
            Auction fetched = em.find(Auction.class, auction.getId());
            fetched.entitySetJoin.add(new Bid());
        });

        System.out.println("=========== e3");
        runInTransaction(() -> {
            Auction fetched = em.find(Auction.class, auction.getId());
            fetched.entitySetJoin.add(new Bid());
        });

    }

    @Test
    public void testDocumentMapping() {
        runInTransaction(() -> em.persist(new DocumentHeavy()));

        assertThat(em.createQuery("FROM DocumentLite").getResultList().size()).isEqualTo(1);
    }

    @Test
    public void testNP1Select() {
        int n = 5;
        runInTransaction(() -> {
            for (int i = 0; i < n; i++) {
                Auction auction = new Auction();
                auction.entityListJoin.add(new Bid());
                auction.entityListJoin.add(new Bid());
                auction.entityListJoin.add(new Bid());
                em.persist(auction);
            }
        });

        runInTransaction(() -> {
            List<Auction> auctions = em.createQuery("FROM Auction a LEFT JOIN FETCH a.entityListJoin").getResultList();
            for (Auction auction : auctions)
                for (Bid bid : auction.entityListJoin)
                    System.out.println(bid);
        });
    }

    @Test
    public void testPurchaseProcess() {
        Customer c = new Customer();
        runInTransaction(() -> em.persist(c));
        purchaseProcess.createOrder(c.getId());
    }

    private void runInTransaction(Runnable r) {
        tt.execute((c) -> {
            r.run();
            return null;
        });
    }

}
