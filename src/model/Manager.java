package model;


import java.io.Serializable;
import java.time.LocalDate;


public class Manager extends Staff implements Serializable {

    private static final Double WAGE = 50.0;
    private static final long serialVersionUID = 1L;
    private String license;

    public Manager(Integer personId, String firstName, String lastName, LocalDate birthDate, Address address, UserDetails userDetails, BankDetails bankDetails, String mailAddress, String license) {
        super(personId, firstName, lastName, birthDate, address, userDetails, bankDetails, mailAddress);
        this.license = license;
    }

    @Override
    public double getWage() {
        return WAGE;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    @Override
    public String toString() {
        return "[id=" + this.getPersonId() + ", first name=" + this.getFirstName() + ", last name =" + this.getLastName() + ", mail =" + this.getMailAddress() + ", userName =" + this.getUserDetails().getUserName() + ", password =" + this.getUserDetails().getPassword() + ", birth date = " + this.getBirthDate() + ", address = " + this.getAddress() + ", role = " + this.getRole() + ",License :" + this.getLicense() + "]";

    }


}
