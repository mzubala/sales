package pl.com.bottega.common.infrastructure.events;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import pl.com.bottega.common.domain.events.Event;
import pl.com.bottega.common.domain.events.EventPublisher;

@Component
public class StandardEventPublisher implements EventPublisher {

    @Autowired
    private ApplicationEventPublisher publisher;

    @Override
    public void publish(Event event) {
        publisher.publishEvent(event);
    }

}
