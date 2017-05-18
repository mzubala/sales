package pl.com.bottega.common.infrastructure;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import pl.com.bottega.common.domain.TestAggregate;

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

}
