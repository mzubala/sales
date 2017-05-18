package pl.com.bottega.common.infrastructure;

import org.springframework.stereotype.Component;
import pl.com.bottega.common.domain.TestAggregate;
import pl.com.bottega.common.infrastructure.repositories.JPABaseRepository;

@Component
public class TestJPARepository extends JPABaseRepository<TestAggregate> {

    public TestJPARepository() {
        super(TestAggregate.class);
    }
}
