package pl.com.bottega.sales.domain;

import pl.com.bottega.common.domain.Address;

import javax.persistence.Embeddable;
import javax.persistence.OneToOne;

@Embeddable
public class CustomerData {

    private String firstName, lastName;

    @OneToOne
    private Address shippingAddress;

    @OneToOne
    private Address billingAddress;

    CustomerData() {}

    public CustomerData(String firstName, String lastName, Address shippingAddress, Address billingAddress) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.shippingAddress = shippingAddress;
        this.billingAddress = billingAddress;
    }

}
