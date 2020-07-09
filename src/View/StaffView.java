package View;

import Controller.StaffController;
import model.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;
import java.util.Set;

public class StaffView {

    private final StaffController staffController;

    public StaffView() throws Exception {
        this.staffController = StaffController.getInstance();
    }


    public void addNewStaff(Scanner scanner) {

        System.out.print("Enter staff id : ");
        String staffId = scanner.nextLine();
        System.out.print("Entr first name : ");
        String fName = scanner.nextLine();
        System.out.print("Enter last name : ");
        String lName = scanner.nextLine();
        System.out.print("Enter mail address : ");
        String mailAddress = scanner.nextLine();
        System.out.print("Enter birth date in this format (dd/mm/yyyy) :");
        String birthDate = scanner.nextLine();

        System.out.print("Enter private house number:");
        String houseNum = scanner.nextLine();
        System.out.print("Enter house street:");
        String houseStreet = scanner.nextLine();
        System.out.print("Enter city:");
        String city = scanner.nextLine();
        System.out.print("Enter state:");
        String state = scanner.nextLine();
        System.out.print("Enter User Name:");
        String username = scanner.nextLine();
        System.out.print("Enter password:");
        String staffPassword = scanner.nextLine();

        System.out.println("BankDetails:");
        System.out.println("enter bank Account Number:");
        String bankAccountNumber = scanner.nextLine();

        System.out.println("brunch Number:");
        String brunchNumber = scanner.nextLine();

        Address address = new Address.AddressBuilder(houseStreet).houseNumber(Integer.parseInt(houseNum)).city(city).state(state).build();
        BankDetails bankDetails = new BankDetails(bankAccountNumber, Integer.parseInt(brunchNumber));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.ENGLISH);
        LocalDate date = LocalDate.parse(birthDate, formatter);
        UserDetails userDetails = new UserDetails(username, staffPassword);


        Staff staff;
        System.out.print("Enter role (manager/employee):");
        String roleUser = scanner.nextLine();

        if (roleUser.equals("employee")) {
            EmployeeType employeeType;

            System.out.print("Enter employee type (shiftManager,minorWorker):");
            roleUser = scanner.nextLine();

            if (roleUser.equals("minorWorker")) {
                System.out.print("Enter employee type (waiter,hostess,massacre):");
                String employeeTypeUser = scanner.nextLine();
                employeeType = EmployeeType.valueOf(employeeTypeUser);

                userDetails.setRole(Role.minorWorker);
            } else {
                employeeType = EmployeeType.shiftManager;
                userDetails.setRole(Role.shiftManager);
            }
            staff = new Employee(Integer.parseInt(staffId), fName, lName, date, address, userDetails, bankDetails, mailAddress, employeeType);


        } else {
            userDetails.setRole(Role.manager);
            staff = new Manager(Integer.parseInt(staffId), fName, lName, date, address, userDetails, bankDetails, mailAddress);
        }

        boolean success = this.staffController.addNewStaff(staff);
        if (success) {
            System.out.println("Staff " + fName + " added successfully");
        } else {
            System.out.println("Failed to add " + fName);
        }
        System.out.println();
    }

    public void viewAllStaff() {

        Set<Staff> staffSet = this.staffController.getAllStaff();
        System.out.println("List of Staff members:");
        for (Staff s : staffSet) {
            System.out.println(s);

        }
    }

    public void deleteStaff(Scanner scanner) {
        System.out.print("Enter staff id you want to remove (number): ");
        String id = scanner.nextLine();

        boolean success = this.staffController.deleteStaff(Integer.parseInt(id));

        if (success) {
            System.out.println("Staff  removed successfully");
        } else {
            System.out.println("Failed to remove ");
        }
        System.out.println();
    }

    public void editStaffPersonalDetails(Scanner scanner) {

        System.out.print("Enter Staff id you want to edit (number): ");
        String id = scanner.nextLine();

        Staff staff = this.staffController.getStaffByID(Integer.parseInt(id));

        if (staff == null) {
            System.out.print("Staff does not exists!");

        } else {
            System.out.print("Enter first name : ");
            String fName = scanner.nextLine();
            System.out.print("Enter last name : ");
            String lName = scanner.nextLine();
            System.out.print("Enter birth date in this format (dd/mm/yyyy) :");
            String birthDate = scanner.nextLine();

            System.out.print("Enter private house number:");
            String houseNum = scanner.nextLine();
            System.out.print("Enter house street:");
            String houseStreet = scanner.nextLine();
            System.out.print("Enter city:");
            String city = scanner.nextLine();
            System.out.print("Enter state:");
            String state = scanner.nextLine();

            System.out.println("BankDetails:");
            System.out.println("enter bank Account Number:");
            String bankAccountNumber = scanner.nextLine();

            System.out.println("brunch Number:");
            String brunchNumber = scanner.nextLine();


            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.ENGLISH);
            LocalDate date = LocalDate.parse(birthDate, formatter);

            staff.setFirstName(fName);
            staff.setLastName(lName);
            staff.setBirthDate(date);

            Address address = new Address.AddressBuilder(houseStreet).houseNumber(Integer.parseInt(houseNum)).city(city).state(state).build();

            staff.setAddress(address);
            BankDetails bankDetails = new BankDetails(bankAccountNumber, Integer.parseInt(brunchNumber));
            staff.setBankDetails(bankDetails);

            boolean success = this.staffController.editStaff(staff);
            if (success) {
                System.out.println("Staff " + fName + " updated successfully");
            } else {
                System.out.println("Failed to update " + fName);
            }
            System.out.println();
        }
    }


    public void editStaffUserDetails(Scanner scanner) {

        System.out.print("Enter Staff id you want to edit (number): ");
        String id = scanner.nextLine();

        Staff staffEdit = staffController.getStaffByID(Integer.parseInt(id));

        if (staffEdit == null) {
            System.out.print("Staff does not exists!");

        } else {
            Role role;
            System.out.print("Enter role (manager/employee):");
            String roleUser = scanner.nextLine();

            if (roleUser.equals("employee")) {
                System.out.print("Enter employee type (shiftManager,minorWorker):");
                roleUser = scanner.nextLine();
            }
            role = Role.valueOf(roleUser);

            System.out.print("Enter User Name:");
            String username = scanner.nextLine();

            System.out.print("Enter password:");
            String staffPassword = scanner.nextLine();

            UserDetails userDetails = new UserDetails(username, staffPassword, role);
            staffEdit.setUserDetails(userDetails);

            boolean success = this.staffController.editStaff(staffEdit);

            if (success) {
                System.out.println("Staff " + id + " updated successfully");
            } else {
                System.out.println("Failed to update " + id);
            }

            System.out.println();


        }
    }
}






