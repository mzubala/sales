package pl.com.bottega.common.domain.events;

public interface EventPublisher {

    void publish(Event event);

}
