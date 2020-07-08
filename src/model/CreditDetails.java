package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

public class CreditDetails implements Serializable {
    private String creditID;
    private LocalDate period;
    private int identificationCode;

    public CreditDetails(String creditID, LocalDate period, int identificationCode) {
        this.creditID = creditID;
        this.period = period;
        this.identificationCode = identificationCode;
    }

    @Override
    public String toString() {
        return "CreditDetails{" +
                "creditID='" + creditID + '\'' +
                ", period=" + period +
                ", identificationCode=" + identificationCode +
                '}';
    }
}
