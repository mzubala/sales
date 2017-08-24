package pl.com.bottega.common.domain;

import com.google.common.base.Objects;

import javax.persistence.*;

import static com.google.common.base.Preconditions.checkNotNull;

@Entity
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String line1;

    private String line2;

    private String zipCode;

    private String city;

    private String state;

    private String country;

    Address() {}

    public Address(String line1, String line2, String zipCode, String city, String state, String country) {
        checkNotNull(line1);
        checkNotNull(zipCode);
        checkNotNull(city);
        checkNotNull(state);
        checkNotNull(country);
        this.line1 = line1;
        this.line2 = line2;
        this.zipCode = zipCode;
        this.city = city;
        this.state = state;
        this.country = country;
    }

    public String getLine1() {
        return line1;
    }

    public String getLine2() {
        return line2;
    }

    public String getZipCode() {
        return zipCode;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getCountry() {
        return country;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equal(line1, address.line1) &&
                Objects.equal(line2, address.line2) &&
                Objects.equal(zipCode, address.zipCode) &&
                Objects.equal(city, address.city) &&
                Objects.equal(state, address.state) &&
                Objects.equal(country, address.country);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(line1, line2, zipCode, city, state, country);
    }
}
