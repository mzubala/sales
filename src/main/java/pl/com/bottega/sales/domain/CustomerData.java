package pl.com.bottega.sales.domain;

import pl.com.bottega.common.domain.Address;

public class CustomerData {

    private String firstName, lastName;

    private Address shippingAddress;

    private Address billingAddress;

    CustomerData() {}

    public CustomerData(String firstName, String lastName, Address shippingAddress, Address billingAddress) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.shippingAddress = shippingAddress;
        this.billingAddress = billingAddress;
    }

}
