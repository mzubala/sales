package pl.com.bottega.common.infrastructure;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;
import pl.com.bottega.common.domain.TestAggregate;
import pl.com.bottega.common.domain.repositories.AggregateNotFoundException;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JPABaseRepositoryTest {

    @Autowired
    private TestJPARepository repository;

    @Test
    @Transactional
    public void savesAggregate() {
        //given
        TestAggregate agg = new TestAggregate();

        //when
        repository.put(agg);

        //then
        TestAggregate getAgg = repository.get(agg.getId());
        assertThat(getAgg).isNotNull();
    }

    @Test(expected = AggregateNotFoundException.class)
    @Transactional
    public void doesNotReturnRemovedAggregates() {
        //given
        TestAggregate agg = new TestAggregate();
        repository.put(agg);

        //when
        repository.remove(agg.getId());

        //then
        repository.get(agg.getId());
    }

    @Test(expected = AggregateNotFoundException.class)
    @Transactional
    public void thorwsErrorWhenAggDoesNotExist() {
        //given

        //when
        repository.get(666L);
    }

    @Test
    @Transactional
    public void returnsOptionalAggregate() {
        //given
        TestAggregate agg = new TestAggregate();
        repository.put(agg);

        //when
        Optional<TestAggregate> optionalExisting = repository.getOptional(agg.getId());
        Optional<TestAggregate> optionalNotExisting = repository.getOptional(666L);

        //then
        assertThat(optionalExisting).isNotEmpty();
        assertThat(optionalNotExisting).isEmpty();

    }

    @Test
    public void allowsMultiplePuts() {
        //given
        TestAggregate agg = new TestAggregate();
        putInTransaction(agg);

        // when
        putInTransaction(agg);
    }

    @Autowired
    private PlatformTransactionManager mgr;

    public void putInTransaction(TestAggregate agg) {
        TransactionTemplate template = new TransactionTemplate(mgr);
        template.execute((callback) -> {
            repository.put(agg);
            return agg;
        });
    }

}
