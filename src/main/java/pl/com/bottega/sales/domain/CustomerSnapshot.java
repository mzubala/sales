package pl.com.bottega.sales.domain;

import pl.com.bottega.common.domain.Address;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;

@Embeddable
@Access(value = AccessType.FIELD)
public class CustomerSnapshot {

    private Long customerId;

    private String firstName;
    private String lastName;

    @Embedded
    private Address shippingAddress;

    public CustomerSnapshot(Long customerId, String firstName, String lastName, Address shippingAddress) {
        this.customerId = customerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.shippingAddress = shippingAddress;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Address getShippingAddress() {
        return shippingAddress;
    }
}
