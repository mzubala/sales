package pl.com.bottega.common.infrastructure.time;

import org.springframework.stereotype.Component;
import pl.com.bottega.common.domain.time.TimeService;

import java.time.Clock;

@Component
public class StandardTimeService implements TimeService {
    @Override
    public Clock clock() {
        return Clock.systemUTC();
    }
}
