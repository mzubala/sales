package pl.com.bottega.common.domain.repositories;

import pl.com.bottega.common.domain.BaseAggregateRoot;

public class AggregateNotFoundException extends RuntimeException {

    public AggregateNotFoundException(Long id, Class<? extends BaseAggregateRoot> klass) {
        super(String.format("No aggregate %s with id %d could be found.", klass.getName(), id));
    }

}
