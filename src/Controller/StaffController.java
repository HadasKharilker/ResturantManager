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

    public boolean addNewStaff(Staff staff) {
        return this.staffService.addNewStaff(staff);
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
