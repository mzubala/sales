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
    void prePersist() {
        this.createdAt = updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
