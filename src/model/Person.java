package model;

import java.io.Serializable;
import java.time.LocalDate;

public class Person implements Serializable {

    private static final long serialVersionUID = 1L;

    private int personId;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private Address address;

    //c'tor



    public Person(Integer personId, String firstName, String lastName, LocalDate birthDate, Integer houseNumber, String street, String city, String state){
        super();
        this.personId = personId;
        this.firstName = firstName;
        this.lastName  = lastName;
        this.birthDate= birthDate;
        this.address=new Address( houseNumber,street,city,state);


    }

    public Person(Integer personId) {
        super();
        this.personId = personId;
    }

    public Person() {
        super();
    }


// getter's and setter's

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }



    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + personId;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Person other = (Person) obj;
        if (personId != other.personId)
            return false;
        return true;
    }




}






