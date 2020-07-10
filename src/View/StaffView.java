package View;

import Controller.StaffController;
import model.*;

import java.security.spec.ECField;
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
        boolean success;

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

        System.out.print("Enter role (manager/employee):");
        String role = scanner.nextLine();

        if (role.equals("employee")) {
            System.out.print("Enter employee type (shiftManager,waiter,hostess,massacre):");
            String employeeType = scanner.nextLine();

            success = this.staffController.addNewEmployee(staffId, fName, lName, birthDate, houseNum,
                    houseStreet, city, username, staffPassword, role,
                    state, bankAccountNumber, brunchNumber, mailAddress, employeeType);

        } else {
            System.out.print("Enter License number:");
            String licenseNum = scanner.nextLine();

            success = this.staffController.addNewManager(staffId, fName, lName, birthDate, houseNum,
                    houseStreet, city, username, staffPassword, role,
                    state, bankAccountNumber, brunchNumber, mailAddress, licenseNum);
        }

        if (success) {
            System.out.println("Staff " + fName + " added successfully");
        } else {
            System.out.println("Failed to add " + fName);
        }
        System.out.println();

    }


    public void editStaffUserDetails(Scanner scanner) {

        System.out.println("Enter Staff id you want to edit (number): ");
        viewAllStaff();
        String staffID = scanner.nextLine();

        //System.out.print("Enter role (manager/employee):");
        //String role = scanner.nextLine();

        System.out.print("Enter User Name:");
        String username = scanner.nextLine();

        System.out.print("Enter password:");
        String staffPassword = scanner.nextLine();

        boolean success = this.staffController.editStaff(staffID, username, staffPassword);

        if (success) {
            System.out.println("editStaffUserDetails successfully");
        } else {
            System.out.println("Failed to editStaffUserDetails");
        }

        System.out.println();


    }

    public void viewAllStaff() {

        boolean success = this.staffController.printAllStaff();
        if (!success) {
            System.out.println("Failed to viewAllStaff ");
        }

        System.out.println();


    }

    public void deleteStaff(Scanner scanner) {
        System.out.println("Enter staff id you want to remove (number): ");
        viewAllStaff();

        String staffID = scanner.nextLine();

        boolean success = this.staffController.deleteStaff(staffID);
        if (success) {
            System.out.println("staff " + staffID + " delete successfully");
        } else {
            System.out.println("Failed to delete staff " + staffID);
        }
        System.out.println();

    }

    public void editStaffPersonalDetails(Scanner scanner) {

        System.out.println("Enter Staff id you want to edit (number): ");
        viewAllStaff();
        String id = scanner.nextLine();

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
        System.out.println("Enter mail :");
        String mail = scanner.nextLine();

        System.out.println("BankDetails:");
        System.out.println("enter bank Account Number:");
        String bankAccountNumber = scanner.nextLine();

        System.out.println("brunch Number:");
        String brunchNumber = scanner.nextLine();

        boolean success = this.staffController.editStaff(id, fName, lName, birthDate, houseNum, houseStreet, city,
                state, bankAccountNumber, brunchNumber, mail);

        if (success) {
            System.out.println("Staff PersonalDetails " + fName + " updated successfully");
        } else {
            System.out.println("Failed to update PersonalDetails-" + fName);
        }
        System.out.println();
    }
}






