package Controller;

import java.time.LocalDate;
import java.util.Locale;
import java.util.Set;

import model.Manager;
import model.Employee;

import java.time.format.DateTimeFormatter;

import model.*;
import model.Service.StaffService;

public class StaffController {

    //for singelton
    private static StaffController INSTANCE;
    private static final Object lockObject = new Object();
    private final StaffService staffService;

    //for singelton
    private StaffController() throws Exception {
        this.staffService = new StaffService();
    }

    //for singelton
    public static StaffController getInstance() throws Exception {
        if (INSTANCE == null) {
            synchronized (lockObject) {
                if (INSTANCE == null) {
                    INSTANCE = new StaffController();
                }
            }
        }

        return INSTANCE;
    }

    public boolean addNewEmployee(String personId, String fName, String lname, String date, String houseNum,
                                  String houseStreet, String city, String username, String staffPassword, String role,
                                  String state, String bankAccountNumber, String brunchNumber, String mail, String employeeType) {
        if (personId != "")
            return this.staffService.addNewEmployee(personId, fName, lname, date, houseNum,
                    houseStreet, city, username, staffPassword, role,
                    state, bankAccountNumber, brunchNumber, mail, employeeType);

        return false;
    }

    public boolean addNewManager(String personId, String fName, String lname, String date, String houseNum,
                                 String houseStreet, String city, String username, String staffPassword, String role,
                                 String state, String bankAccountNumber, String brunchNumber, String mail, String licenseNum) {

        if (personId != "")

            return this.staffService.addNewManager(personId, fName, lname, date, houseNum,
                    houseStreet, city, username, staffPassword, role,
                    state, bankAccountNumber, brunchNumber, mail, licenseNum);

        return false;
    }


    public boolean deleteStaff(String id) {
        if (id != "")
            return this.staffService.deleteStaff(id);

        return false;
    }

    public boolean printAllStaff() {
        return staffService.printAllStaff();
    }

    public boolean editStaff(String personId, String fName, String lname, String date, String houseNum, String houseStreet, String city,
                             String state, String bankAccountNumber, String brunchNumber, String mail) {
        if (personId != "")
            return this.staffService.editStaff(personId, fName, lname, date, houseNum, houseStreet, city,
                    state, bankAccountNumber, brunchNumber, mail);

        return false;


    }

    public boolean editStaff(String StaffID, String role, String username, String staffPassword) {
        if (StaffID != "")
            return this.staffService.editStaff(StaffID, role, username, staffPassword);

        return false;
    }

}
