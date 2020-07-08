package model;

import java.io.Serializable;
import java.time.LocalDate;

public class Client extends Person implements Serializable {
    private CreditDetails creditDetails;
    private boolean pushOn;
    private static final long serialVersionUID = 1L;

    public Client(Integer personId, String firstName, String lastName, LocalDate birthDate, Address address,String mailAddress ,CreditDetails creditDetails, boolean pushOn) {
        super(personId, firstName, lastName, birthDate, address,mailAddress);
        this.creditDetails = creditDetails;
        this.pushOn = pushOn;
    }

    public void setCreditDetails(CreditDetails creditDetails) {
        this.creditDetails = creditDetails;
    }

    public CreditDetails getCreditDetails() {
        return creditDetails;
    }

    public void setPushOn(boolean pushOn) {
        this.pushOn = pushOn;
    }

    public boolean isPushOn() {
        return pushOn;
    }

    @Override
    public String toString() {
        return "[id=" + this.getPersonId() + ", name=" + this.getFirstName() + " "+this.getLastName() + " mail=" + this.getMailAddress() + " ,notification -"+this.isPushOn() +"]";

    }

}
