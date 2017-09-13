package pl.com.bottega.common.infrastructure.repositories;

import pl.com.bottega.common.domain.BaseAggregateRoot;
import pl.com.bottega.common.domain.repositories.AggregateNotFoundException;
import pl.com.bottega.common.domain.repositories.Repository;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;
import java.lang.reflect.ParameterizedType;
import java.util.Optional;

public abstract class JPABaseRepository<Aggregate extends BaseAggregateRoot> implements Repository<Aggregate> {

    @PersistenceContext
    protected EntityManager entityManager;

    private Class<Aggregate> aggregateClass;

    public JPABaseRepository() {
        this.aggregateClass = ((Class<Aggregate>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0]);
    }

    @Override
    public Aggregate get(Long id) throws AggregateNotFoundException {
        return getOptional(id).orElseThrow(() -> new AggregateNotFoundException(id, aggregateClass));
    }

    @Override
    public Optional<Aggregate> getOptional(Long id) {
        Aggregate agg = entityManager.find(aggregateClass, id, LockModeType.OPTIMISTIC);
        if (agg != null && agg.isRemoved())
            agg = null;
        return Optional.ofNullable(agg);
    }

    @Override
    public void put(Aggregate aggregate) {
        if (entityManager.contains(aggregate))
            entityManager.lock(aggregate, LockModeType.OPTIMISTIC_FORCE_INCREMENT);
        else
            entityManager.persist(aggregate);
    }

    @Override
    public void remove(Long id) {
        get(id).remove();
    }


}
