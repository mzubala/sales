package pl.com.bottega.common.domain;

import pl.com.bottega.common.domain.events.EventPublisher;
import pl.com.bottega.common.domain.events.EventPublisherAware;
import pl.com.bottega.common.domain.time.TimeService;
import pl.com.bottega.common.domain.time.TimeServiceAware;

import javax.persistence.Entity;
import javax.persistence.Transient;

@Entity
public class TestAggregate extends BaseAggregateRoot implements TimeServiceAware, EventPublisherAware {

    @Transient
    private TimeService timeService;

    @Transient
    private EventPublisher eventPublisher;

    @Override
    public void setTimeService(TimeService timeService) {
        this.timeService = timeService;
    }

    @Override
    public void setEventPublisher(EventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    public TimeService getTimeService() {
        return timeService;
    }
}
