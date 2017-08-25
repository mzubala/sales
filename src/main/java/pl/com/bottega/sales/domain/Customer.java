package pl.com.bottega.sales.domain;

import pl.com.bottega.common.domain.Address;
import pl.com.bottega.common.domain.BaseAggregateRoot;

import javax.persistence.*;

@Entity
public class Customer extends BaseAggregateRoot {

    private String firstName;
    private String lastName;

    @Enumerated(EnumType.STRING)
    private CustomerStatus status;

    @OneToOne(optional = false, cascade = CascadeType.ALL)
    private Address shippingAddress;

    @OneToOne(optional = false, cascade = CascadeType.ALL)
    private Address billingAddress;

    Customer() {}

    public Customer(String firstName, String lastName, CustomerStatus status, Address shippingAddress, Address billingAddress) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.status = status;
        this.shippingAddress = shippingAddress;
        this.billingAddress = billingAddress;
    }

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

    public Address getBillingAddress() {
        return billingAddress;
    }

    public CustomerData getSnapshot() {
        return new CustomerData(firstName, lastName, shippingAddress, billingAddress);
    }
}
