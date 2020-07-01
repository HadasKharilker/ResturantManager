package model;

import java.io.Serializable;
import java.time.LocalDate;



public class Staff extends Person implements Serializable {
    private UserDetails userDetails;
    private static final long serialVersionUID = 1L;


    public Staff(Integer personId, String firstName, String lastName, LocalDate birthDate, Integer houseNumber, String street, String city, String state,String userName,String password, Role role) {
        super(personId, firstName, lastName, birthDate, houseNumber, street, city, state);
        this.userDetails=new UserDetails(userName,password,role);

    }

    public Staff(Integer id) {
        super(id);


    }

    public Staff() {
        super();
    }


    public UserDetails getUserDetails() {
        return userDetails;
    }
    public Role getRole() {
        return userDetails.getRole();
    }

    public void setUserDetails(UserDetails userDetails) {
        this.userDetails = userDetails;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + getPersonId();
        return result;
    }




public boolean isPassowrdCuurect(String password){

       if( this.getUserDetails().getPassword()==password)
           return true;

           return false;

}

//function clock oin(call repository -> in repository do function that insert current time to file )


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

    @Override
    public String toString() {
        return "[id=" + this.getPersonId() + ", first name=" + this.getFirstName() + ", last name =" + this.getLastName() + "]";

    }
}
