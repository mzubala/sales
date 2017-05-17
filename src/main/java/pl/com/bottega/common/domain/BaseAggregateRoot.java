package pl.com.bottega.common.domain;

import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
public abstract class BaseAggregateRoot extends BaseEntity {

    private boolean removed;

    private Long version;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public void remove() {

    }

    public boolean isRemoved() {
        return false;
    }

}
