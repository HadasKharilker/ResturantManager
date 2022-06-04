package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

public class CreditDetails implements Serializable {
    private String creditID;
    private LocalDate period;
    private int identificationCode;

    public CreditDetails(CreditDetailsBuilder creditDetailsBuilder) {
        this.creditID = creditDetailsBuilder.creditID;
        this.period = creditDetailsBuilder.period;
        this.identificationCode = creditDetailsBuilder.identificationCode;
    }

    public int getIdentificationCode() {
        return identificationCode;
    }

    public LocalDate getPeriod() {
        return period;
    }

    public String getCreditID() {
        return creditID;
    }

    public void setCreditID(String creditID) {
        this.creditID = creditID;
    }

    public void setIdentificationCode(int identificationCode) {
        this.identificationCode = identificationCode;
    }

    public void setPeriod(LocalDate period) {
        this.period = period;
    }

    @Override
    public String toString() {
        return "CreditDetails{" +
                "creditID='" + creditID + '\'' +
                ", period=" + period +
                ", identificationCode=" + identificationCode +
                '}';
    }

    public static class CreditDetailsBuilder {

        private String creditID;
        private LocalDate period;
        private int identificationCode;

        public CreditDetailsBuilder(String creditID) {
            this.creditID = creditID;
        }

        public CreditDetailsBuilder period(LocalDate period) {
            this.period = period;
            return this;
        }

        public CreditDetailsBuilder code(int identificationCode) {
            this.identificationCode = identificationCode;
            return this;
        }


        public CreditDetails build() {
            CreditDetails creditDetails = new CreditDetails(this);
            validateUserObject(creditDetails);
            return creditDetails;
        }

        private void validateUserObject(CreditDetails creditDetails) {
            //Do some basic validations to check
            //if user object does not break any assumption of system
        }
    }
}
