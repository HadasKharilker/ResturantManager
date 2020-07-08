package model;

import java.io.Serializable;
import java.util.Date;

public class BankDetails implements Serializable {
    private String bankAccountNumber;
    private int brunchNumber;

    public BankDetails(String bankAccountNumber,int brunchNumber) {
        this.bankAccountNumber = bankAccountNumber;
        this.brunchNumber = brunchNumber;
    }
}
