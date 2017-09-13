package pl.com.bottega.common.domain;

import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Version;
import java.time.LocalDateTime;

@MappedSuperclass
public abstract class BaseAggregateRoot extends BaseEntity {

    private boolean removed;

    @Version
    private Long version;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public void remove() {
        this.removed = true;
    }

    public boolean isRemoved() {
        return removed;
    }

    public Long getVersion() {
        return version;
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
