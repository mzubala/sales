package pl.com.bottega.common.domain.repositories;

import pl.com.bottega.common.domain.BaseAggregateRoot;

import java.util.Optional;

public interface Repository<Aggregate extends BaseAggregateRoot> {

    Aggregate get(Long id) throws AggregateNotFoundException;

    Optional<Aggregate> getOptional(Long id);

    void put(Aggregate aggregate);

    void remove(Long id);

}
