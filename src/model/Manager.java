package model;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.util.Locale;

import java.util.Date;
import java.io.Serializable;

public class Manager extends Staff implements Serializable{

    private static final Double WAGE=50.0;
    private static final long serialVersionUID = 1L;

    public Manager(Integer personId, String firstName, String lastName, LocalDate birthDate, Integer houseNumber, String street, String city, String state, String userName, String password, Role role) {
        super(personId, firstName, lastName, birthDate, houseNumber, street, city, state, userName, password,role);

    }

    public Manager(Integer id) {
        super(id);
    }


    public Manager() {
        super();
    }

    @Override
    public String toString() {
        return "[id=" + this.getPersonId() + ", first name=" + this.getFirstName() + ", last name =" + this.getLastName() + ", birth date = "+ this.getBirthDate() +", address = " + this.getAddress() +", role = " + this.getRole()+"]";

    }




}
