package pl.com.bottega.play;

import pl.com.bottega.common.domain.BaseEntity;

import javax.persistence.*;
import java.util.*;

@Entity
@Table
public class Auction extends BaseEntity {

    @ElementCollection
    Collection<String> bag = new LinkedList<>();

    @ElementCollection
    @Column(name = "value")
    Set<String> set = new HashSet<>();

    @ElementCollection
    @OrderColumn
    List<String> list = new LinkedList<>();

    @OneToMany(cascade = CascadeType.ALL)
    Collection<Bid> entityBagLink = new LinkedList<>();

    @OneToMany(cascade = CascadeType.ALL)
    Set<Bid> entitySetLink = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL)
    @OrderColumn
    List<Bid> entityListLink = new LinkedList<>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn
    Collection<Bid> entityBagJoin = new LinkedList<>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn
    Set<Bid> entitySetJoin = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL)
    @OrderColumn
    List<Bid> entityListJoin = new LinkedList<>();


}
