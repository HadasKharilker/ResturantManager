package model;


import java.io.Serializable;
import java.time.LocalDate;


public class Manager extends Staff implements Serializable {
    //here2
    private static final Double WAGE = 50.0;
    private static final long serialVersionUID = 1L;

    public Manager(Integer personId, String firstName, String lastName, LocalDate birthDate, Address address, UserDetails userDetails, BankDetails bankDetails, String mailAddress) {
        super(personId, firstName, lastName, birthDate, address, userDetails, bankDetails, mailAddress);

    }

    @Override
    public double getWage() {
        return WAGE;
    }

    public Manager(Integer id) {
        super(id);
    }

    public static Double getWAGE() {
        return WAGE;
    }

    public Manager() {
        super();
    }

/*
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + getPersonId();
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
        Staff other = (Staff) obj;
        if (getPersonId() != other.getPersonId())
            return false;
        return true;
    }
    */



    @Override
    public String toString() {
        return "[id=" + this.getPersonId() + ", first name=" + this.getFirstName() + ", last name =" + this.getLastName() + ", mail =" + this.getMailAddress() + ", userName =" + this.getUserDetails().getUserName() + ", password =" + this.getUserDetails().getPassword() + ", birth date = " + this.getBirthDate() + ", address = " + this.getAddress() + ", role = " + this.getRole() + "]";

    }


}
