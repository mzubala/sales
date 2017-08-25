package pl.com.bottega.sales.domain;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import pl.com.bottega.common.domain.Address;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.OneToOne;

@Embeddable
public class CustomerData {

    private String firstName, lastName;

    @OneToOne(cascade = CascadeType.ALL, optional = false)
    private Address shippingAddress;

    @OneToOne(cascade = CascadeType.ALL, optional = false)
    private Address billingAddress;

    CustomerData() {}

    public CustomerData(String firstName, String lastName, Address shippingAddress, Address billingAddress) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.shippingAddress = shippingAddress;
        this.billingAddress = billingAddress;
    }

}
