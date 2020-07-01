package model;

import java.io.Serializable;

public class Address implements Serializable {
    private static final long serialVersionUID = 1L;
    private int houseNumber;
    private String street;
    private String city;
    private String state;


   public Address(Integer houseNumber,String street,String city,String state)
   {
       this.houseNumber = houseNumber;
       this.street = street;
       this.city  = city;
       this.state=state;
   }
   /*
   setter's and getter's

    */

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(int houseNumber) {
        this.houseNumber = houseNumber;
    }


    @Override
    public String toString() {
        return "[House number=" + houseNumber + ", Street=" + street + ", City=" + city + "]";

    }

}
