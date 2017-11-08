package pl.com.bottega.common.infrastructure;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.support.TransactionTemplate;
import pl.com.bottega.common.domain.TestAggregate;
import pl.com.bottega.common.domain.TestRepository;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest
public class JpaBaseRepositoryTest {

    @Autowired
    private TestRepository testRepository;

    @Autowired
    private TransactionTemplate transactionTemplate;

    @Test
    public void savesAndLoadsAggregate() {
        // given
        TestAggregate agg = new TestAggregate();

        // when
        transactionTemplate.execute((c) -> {
            testRepository.put(agg);
            return null;
        });

        // then
        TestAggregate aggLoaded = testRepository.get(agg.getId());
        assertThat(aggLoaded).isNotNull();
        assertThat(aggLoaded.getId()).isEqualTo(agg.getId());
    }

    @Test
    public void canSaveAfterLoad() {
        // given
        TestAggregate agg = new TestAggregate();
        transactionTemplate.execute((c) -> {
            testRepository.put(agg);
            return null;
        });

        // when
        transactionTemplate.execute((c) -> {
            TestAggregate loadedAgg = testRepository.get(agg.getId());
            testRepository.put(loadedAgg);
            return null;
        });
    }

}
