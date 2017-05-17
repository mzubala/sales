package pl.com.bottega.common.domain.time;

import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;

public interface TimeService {

    Clock clock();

    default LocalDateTime now() {
        return LocalDateTime.now(clock());
    }

    default LocalDate today() {
        return LocalDate.now(clock());
    }

}
