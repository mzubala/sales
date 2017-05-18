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
        return getOptional(id).orElseThrow(() -> new AggregateNotFoundException(id, aggregateClass));
    }

    @Override
    public Optional<Aggregate> getOptional(Long id) {
        Aggregate aggregate = entityManager.find(aggregateClass, id);
        if (aggregate == null || aggregate.isRemoved())
            return Optional.empty();
        return Optional.of(aggregate);
    }

    @Override
    public void put(Aggregate aggregate) {
        if (aggregate.getId() == null)
            entityManager.persist(aggregate);
    }

    @Override
    public void remove(Long id) {
        Aggregate aggregate = get(id);
        aggregate.remove();
        // entityManager.remove(aggregate);
    }


}
