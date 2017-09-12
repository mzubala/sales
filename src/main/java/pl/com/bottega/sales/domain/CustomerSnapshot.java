package pl.com.bottega.sales.domain;

import pl.com.bottega.common.domain.Address;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;

@Embeddable
public class CustomerSnapshot {

    private String firstName, lastName;

    @Embedded
    private Address address;

    private Long customerId;

    CustomerSnapshot() {
    }

    public CustomerSnapshot(Long id, String firstName, String lastName, Address address) {
        this.customerId = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
    }

    public Long getId() {
        return customerId;
    }
}
