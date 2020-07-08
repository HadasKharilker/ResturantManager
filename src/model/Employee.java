package model;

import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.util.Locale;
import java.io.Serializable;

public class Employee extends Staff implements Serializable {

    private static final double WAGE = 28.8;
    private static final long serialVersionUID = 1L;


    @Override
    public double getWage() {
        return WAGE;
    }

    public Employee(Integer personId, String firstName, String lastName, LocalDate birthDate, Address address, UserDetails userDetails, BankDetails bankDetails,String mailAddress) {
        super(personId, firstName, lastName, birthDate, address, userDetails, bankDetails,mailAddress);

    }

    public Employee(Integer id) {
        super(id);

    }


    @Override
    public String toString() {
        return "[id=" + this.getPersonId() + ", first name=" + this.getFirstName() + ", last name =" + this.getLastName() +", mail =" + this.getMailAddress() + ", userName =" + this.getUserDetails().getUserName() + ", password =" + this.getUserDetails().getPassword() + ", birth date = " + this.getBirthDate() + ", address = " + this.getAddress() + ", role = " + this.getRole() + "]";
    }


}
