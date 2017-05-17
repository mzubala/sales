package pl.com.bottega.infrastructure;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.com.bottega.common.domain.TestAggregate;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class JPAInjectingListenerTest {

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    public void setsTimeService() {
        TestAggregate aggregate = new TestAggregate();
        entityManager.persist(aggregate);
        entityManager.flush();

        entityManager.refresh(aggregate);

        assertThat(aggregate.getTimeService()).isNotNull();
    }

}
