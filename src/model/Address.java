package model;

import java.io.Serializable;

public class Address implements Serializable {
    private static final long serialVersionUID = 1L;
    private int houseNumber;
    private String street;
    private String city;
    private String state;

    public Address(AddressBuilder addressBuilder) {
        this.houseNumber = addressBuilder.houseNumber;
        this.street = addressBuilder.street;
        this.city = addressBuilder.city;
        this.state = addressBuilder.state;
    }

    @Override
    public String toString() {
        return "[House number=" + houseNumber + ", Street=" + street + ", City=" + city + "]";

    }

    public static class AddressBuilder {

        private int houseNumber;
        private String street;
        private String city;
        private String state;

        public AddressBuilder(String street) {
            this.street = street;
        }

        public AddressBuilder houseNumber(int houseNumber) {
            this.houseNumber = houseNumber;
            return this;
        }

        public AddressBuilder city(String city) {
            this.city = city;
            return this;
        }

        public AddressBuilder state(String state) {
            this.state = state;
            return this;
        }


        public Address build() {
            Address address = new Address(this);
            validateUserObject(address);
            return address;
        }

        private void validateUserObject(Address address) {
            //Do some basic validations to check
            //if user object does not break any assumption of system
        }
    }

}
