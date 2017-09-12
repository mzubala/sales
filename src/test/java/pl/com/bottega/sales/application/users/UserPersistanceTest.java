package pl.com.bottega.sales.application.users;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.support.TransactionTemplate;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserPersistanceTest {

    private UserFactory factory = new UserFactory();

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private TransactionTemplate tt;

    @Test
    public void testUserRoles() {
        User user = factory.standardUser("a", "a");
        runInTransaction(() -> em.persist(user));

        runInTransaction(() -> {
            User u = (User) em.createQuery("FROM UserCore").getResultList().get(0);
            u.addRole(new InvoiceCorrectorRole());
            u.addRole(new OrderCorrectorRole());
        });

        runInTransaction(() -> {
            User u = (User) em.createQuery("FROM UserCore").getResultList().get(0);
            assertThat(u.getRole(InvoiceCorrectorRole.class)).isNotNull();
            assertThat(u.getRole(OrderCorrectorRole.class)).isNotNull();
        });
    }

    private void runInTransaction(Runnable r) {
        tt.execute((c) -> {
            r.run();
            return null;
        });
    }

}
