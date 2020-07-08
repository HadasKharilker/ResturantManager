package Controller;
import java.time.LocalDate;
import java.util.Locale;
import java.util.Set;
import model.Manager;
import model.Employee;
import model.Role;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;

import model.*;

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


    public boolean addNewStaff(String personId, String firstName, String lastName, String birthDate, String houseNumber, String street, String city, String state, String userName, String password, String role) {
        // validations
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.ENGLISH);
        LocalDate date = LocalDate.parse(birthDate, formatter);


        if (role.equals("manager")) {
            Manager staff = new Manager(Integer.parseInt(personId), firstName, lastName, date, Integer.parseInt(houseNumber), street, city, state, userName, password, Role.valueOf(role));
            return this.staffService.addNewStaff(staff);
        }
        if (role.equals("employee")) {
            Employee staff = new Employee(Integer.parseInt(personId), firstName, lastName, date, Integer.parseInt(houseNumber), street, city, state, userName, password, Role.valueOf(role));
            return this.staffService.addNewStaff(staff);
        }
        return false;

    }

    public Set<Staff> getAllStaff() {
        Set<Staff> staff = this.staffService.getAllStaff();
        return staff;
    }


    public boolean deleteStaff(Integer id) {

        return this.staffService.deleteStaff(id);

    }

    public boolean editStaffPersonalDetails(String personId, String firstName, String lastName, String birthDate, String houseNumber, String street, String city, String state) {
        // validations
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.ENGLISH);
        LocalDate date = LocalDate.parse(birthDate, formatter);

        Staff staff = new Staff((Integer.parseInt(personId)), firstName, lastName, date, Integer.parseInt(houseNumber), street, city, state);

        return this.staffService.editStaffPersonalDetails(staff);



    }

    public boolean editStaffUserDetails(String id, String username, String password, String role) {
        // validations

        Staff staff = new Staff((Integer.parseInt(id)),username, password, Role.valueOf(role));

        return this.staffService.editStaffUserDetails(staff);

    }









    public boolean isExist(Integer id) {

        return this.staffService.isExist(id);

    }









}
