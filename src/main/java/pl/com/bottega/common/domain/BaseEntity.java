package pl.com.bottega.common.domain;

import pl.com.bottega.common.infrastructure.repositories.JPAInjectingListener;

import javax.persistence.*;
import java.util.UUID;

@MappedSuperclass
@EntityListeners(JPAInjectingListener.class)
public abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Long getId() {
        return id;
    }
}
