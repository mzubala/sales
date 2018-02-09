package pl.com.bottega.common.infrastructure.repositories;

import pl.com.bottega.common.domain.BaseAggregateRoot;
import pl.com.bottega.common.domain.repositories.AggregateNotFoundException;
import pl.com.bottega.common.domain.repositories.Repository;

import javax.persistence.EntityManager;
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
    return getOptional(id).orElseThrow(
        () -> new AggregateNotFoundException(id, aggregateClass)
    );
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
    entityManager.persist(aggregate);
  }

  @Override
  public void remove(Long id) {
    getOptional(id).ifPresent(BaseAggregateRoot::remove);
  }


}
