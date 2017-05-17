package pl.com.bottega.common.domain.events;

import pl.com.bottega.common.domain.BaseAggregateRoot;

import java.time.LocalDateTime;

public abstract class Event {

    private LocalDateTime publishedAt;

    private Long aggregateId;

    public Event(BaseAggregateRoot aggregate, LocalDateTime t) {
        this.aggregateId = aggregate.getId();
        this.publishedAt = t;
    }

    public Event(Long aggregateId, LocalDateTime t) {
        this.aggregateId = aggregateId;
        this.publishedAt = t;
    }

    public LocalDateTime getPublishedAt() {
        return publishedAt;
    }

    public Long getAggregateId() {
        return aggregateId;
    }
}
