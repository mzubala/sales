package pl.com.bottega.common.domain;

import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;

@MappedSuperclass
public abstract class BaseAggregateRoot extends BaseEntity {

    private boolean removed;

    private Long version;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public void remove() {
        this.removed = true;
    }

    public boolean isRemoved() {
        return removed;
    }

    @PrePersist
    private void assignCreatedAndUpdatedAt() {
        updatedAt = createdAt = LocalDateTime.now();
    }

    @PreUpdate
    private void assignUpdatedAt() {
        updatedAt = LocalDateTime.now();
    }

}
