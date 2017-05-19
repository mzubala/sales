package pl.com.bottega.mappingplay;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import pl.com.bottega.common.domain.BaseEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Cacheable
class Auction extends BaseEntity {

    @OneToMany(cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.EXTRA)
    private List<Bid> bids = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    @OrderColumn
    private List<Photo> photos = new ArrayList<>();

    @Version
    private Long versionId;

    private int total;

    Auction() {
    }

    void add(Bid bid) {
        bids.add(bid);
        total += 1;
    }

    void add(Photo photo) {
        photos.add(photo);
    }

    public void clearBids() {
        bids.clear();
    }
}
