package View;

import Controller.MenuController;
import Controller.StaffController;
import model.MenuItemType;
import model.Role;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;
public class StaffView {

    private final StaffController staffController;

    public StaffView() throws Exception {
        this.staffController = StaffController.getInstance();
    }


    public void addNewStaff(Scanner scanner) {
        System.out.print("Enter staff id : ");
        String staffId = scanner.nextLine();
        System.out.print("Enter first name : ");
        String fName = scanner.nextLine();
        System.out.print("Enter last name : ");
        String lName = scanner.nextLine();
        System.out.print("Enter birth date in this format (dd/mm/yyyy) :");
        String date = scanner.nextLine();
        System.out.print("Enter private house number:");
        String houseNum = scanner.nextLine();
        System.out.print("Enter house street:");
        String houseStreet = scanner.nextLine();
        System.out.print("Enter city:");
        String city = scanner.nextLine();
        System.out.print("Enter state:");
        String state = scanner.nextLine();
        System.out.print("Enter role (manager/employee):");
        String role = scanner.nextLine();
        System.out.print("Enter User Name:");
        String username = scanner.nextLine();
        System.out.print("Enter password:");
        String staffPassword = scanner.nextLine();
        //parsing to date format


        boolean success = this.staffController.addNewStaff(staffId, fName, lName, date, houseNum, houseStreet, city, state, username, staffPassword, role);
        if (success) {
            System.out.println("Staff " + fName + " added successfully");
        } else {
            System.out.println("Failed to add " + fName);
        }
        System.out.println();
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

        if (staffController.isExist(Integer.parseInt(id)) == false) {
            System.out.print("Staff does not exists!");

        } else {
            System.out.print("Enter first name : ");
            String fName = scanner.nextLine();
            System.out.print("Enter last name : ");
            String lName = scanner.nextLine();
            System.out.print("Enter birth date in this format (dd/mm/yyyy) :");
            String date = scanner.nextLine();

            System.out.print("Enter private house number:");
            String houseNum = scanner.nextLine();
            System.out.print("Enter house street:");
            String houseStreet = scanner.nextLine();
            System.out.print("Enter city:");
            String city = scanner.nextLine();
            System.out.print("Enter state:");
            String state = scanner.nextLine();


            boolean success = this.staffController.editStaffPersonalDetails(id, fName, lName, date, houseNum, houseStreet, city, state);
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

        if (staffController.isExist(Integer.parseInt(id)) == false) {
            System.out.print("Staff does not exists!");

        } else {
            System.out.print("Enter role (manager/employee):");
            String role = scanner.nextLine();
            System.out.print("Enter User Name:");
            String username = scanner.nextLine();
            System.out.print("Enter password:");
            String staffPassword = scanner.nextLine();


            boolean success = this.staffController.editStaffUserDetails(id, username, staffPassword, role);

            if (success) {
                System.out.println("Staff " + id + " updated successfully");
            } else {
                System.out.println("Failed to update " + id);
            }
            System.out.println();



        }
    }
}






