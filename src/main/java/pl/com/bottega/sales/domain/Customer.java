package pl.com.bottega.sales.domain;

import pl.com.bottega.common.domain.Address;
import pl.com.bottega.common.domain.BaseAggregateRoot;

import javax.persistence.AssociationOverrides;
import javax.persistence.AttributeOverride;
import javax.persistence.Embedded;
import javax.persistence.Entity;

@Entity
public class Customer extends BaseAggregateRoot {

    private String firstName;
    private String lastName;

    private CustomerStatus status;

    @Embedded
    private Address shippingAddress;

    //@Embedded
    //private Address billingAddress;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public CustomerStatus getStatus() {
        return status;
    }

    public Address getShippingAddress() {
        return shippingAddress;
    }

    //public Address getBillingAddress() {
    //    return billingAddress;
    //}

    public CustomerSnapshot getSnapshot() {
        return new CustomerSnapshot(getId(), firstName, lastName, shippingAddress);
    }
}
