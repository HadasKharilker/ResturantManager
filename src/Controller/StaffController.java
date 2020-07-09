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


    public boolean addNewStaff(String personId, String firstName, String lastName, String birthDate, Address address, UserDetails userDetails, BankDetails bankDetails, String mailAddress) {
        // validations
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.ENGLISH);
        LocalDate date = LocalDate.parse(birthDate, formatter);


        if (userDetails.getRole().equals(Role.valueOf("manager"))) {
            Manager staff = new Manager(Integer.parseInt(personId), firstName, lastName, date, address, userDetails, bankDetails, mailAddress);
            return this.staffService.addNewStaff(staff);
        }
        if (((userDetails.getRole()).equals(Role.valueOf("employee")))) {
            Employee staff = new Employee(Integer.parseInt(personId), firstName, lastName, date, address, userDetails, bankDetails, mailAddress);
            return this.staffService.addNewStaff(staff);
        }
        return false;

    }

    public Set<Staff> getAllStaff() {
        Set<Staff> staff = this.staffService.getAllStaff();
        return staff;
    }


    public boolean deleteStaff(int id) {

        return this.staffService.deleteStaff(id);

    }

    public boolean editStaff(Staff staff) {

        return this.staffService.editStaff(staff);


    }


    public Staff getStaffByID(int id) {

        return this.staffService.getStaffByID(id);


    }


    public boolean isExist(Integer id) {

        return this.staffService.isExist(id);

    }



}
