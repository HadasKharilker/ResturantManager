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

    public int getBrunchNumber() {
        return brunchNumber;
    }

    public void setBankAccountNumber(String bankAccountNumber) {
        this.bankAccountNumber = bankAccountNumber;
    }

    public String getBankAccountNumber() {
        return bankAccountNumber;
    }

    public void setBrunchNumber(int brunchNumber) {
        this.brunchNumber = brunchNumber;
    }

    @Override
    public String toString() {
        return "BankDetails{" +
                "bankAccountNumber='" + bankAccountNumber + '\'' +
                ", brunchNumber=" + brunchNumber +
                '}';
    }
}
