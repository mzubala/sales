package pl.com.bottega.common.infrastructure;

import org.springframework.stereotype.Component;
import pl.com.bottega.common.domain.TestAggregate;
import pl.com.bottega.common.domain.TestRepository;
import pl.com.bottega.common.infrastructure.repositories.JPABaseRepository;

@Component
public class JpaTestRepository extends JPABaseRepository<TestAggregate> implements TestRepository {
}
