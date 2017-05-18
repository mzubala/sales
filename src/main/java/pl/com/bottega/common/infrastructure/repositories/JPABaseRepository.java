package pl.com.bottega.common.infrastructure.repositories;

import pl.com.bottega.common.domain.BaseAggregateRoot;
import pl.com.bottega.common.domain.repositories.AggregateNotFoundException;
import pl.com.bottega.common.domain.repositories.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

public abstract class JPABaseRepository<Aggregate extends BaseAggregateRoot> implements Repository<Aggregate> {

    @PersistenceContext
    protected EntityManager entityManager;

    private Class<Aggregate> aggregateClass;

    public JPABaseRepository(Class<Aggregate> aggregateClass) {
        this.aggregateClass = aggregateClass;
    }

    @Override
    public Aggregate get(Long id) throws AggregateNotFoundException {
        return entityManager.find(aggregateClass, id);
    }

    @Override
    public Optional<Aggregate> getOptional(Long id) {
        return null;
    }

    @Override
    public void put(Aggregate aggregate) {
        entityManager.persist(aggregate);
    }

    @Override
    public void remove(Long id) {

    }


}
