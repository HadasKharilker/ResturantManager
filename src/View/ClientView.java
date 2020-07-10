package View;

import Controller.ClientController;
import Controller.MenuController;
import Controller.OrderController;
import model.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Locale;
import java.util.Scanner;
import java.util.Set;

public class ClientView {

    private final ClientController clientController;

    public ClientView() throws Exception {
        this.clientController = ClientController.getInstance();
    }

    public void updateClient(Scanner scanner) throws Exception {

        System.out.println("select client id to edit from list : ");
        viewAllClients();
        String clientID = scanner.nextLine();

        System.out.println("choose what you want to edit: ");
        System.out.println("1. name");
        System.out.println("2. birth date");
        System.out.println("3. address");
        System.out.println("4. Credit details");
        System.out.println("5. mail address");
        System.out.println("6. notifications");

        String selectedOption = scanner.nextLine();
        Boolean success = false;
        switch (selectedOption) {
            case "1":
                System.out.println("enter first name");
                String firstName = scanner.nextLine();
                System.out.println("enter last name");
                String lastName = scanner.nextLine();

                success = this.clientController.editClientName(clientID, firstName, lastName);
                break;

            case "2":
                System.out.print("Enter birth date in this format (dd/mm/yyyy) :");
                String date = scanner.nextLine();
                success = this.clientController.editBdayClient(clientID, date);
                break;

            case "3":
                System.out.print("Enter private house number:");
                String houseNum = scanner.nextLine();
                System.out.print("Enter house street:");
                String houseStreet = scanner.nextLine();
                System.out.print("Enter city:");
                String city = scanner.nextLine();
                System.out.print("Enter state:");
                String state = scanner.nextLine();

                success = this.clientController.editAddress(clientID, houseStreet, houseNum, city, state);
                break;

            case "4":
                System.out.println("Credit details:");
                System.out.println("enter creditID:");
                String creditID = scanner.nextLine();

                System.out.println("enter period in this format (dd/mm/yyyy):");
                String period = scanner.nextLine();

                System.out.println("enter identificationCode:");
                String identificationCode = scanner.nextLine();

                success = this.clientController.editCreditCard(clientID, creditID, period, identificationCode);

                break;

            case "5":
                System.out.println("enter mail address:");
                String mailAddress = scanner.nextLine();

                success = this.clientController.editMailAddress(clientID, mailAddress);
                break;

            case "6":
                System.out.println("Want to get push? 1-yes 0-no:");
                String pushOnFromUser = scanner.nextLine();

                success = this.clientController.editPushOn(clientID, pushOnFromUser);

                break;
        }

        if (success) {
            System.out.println("client update successfully");
        } else {
            System.out.println("Failed to update client");
        }


    }
    public void addNewClient(Scanner scanner) {

        System.out.print("Enter person id : ");
        String personId = scanner.nextLine();
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
        System.out.println("Credit details:");
        System.out.println("enter creditID:");
        String creditID = scanner.nextLine();
        System.out.println("enter period in this format (dd/mm/yyyy):");
        String period = scanner.nextLine();
        System.out.println("enter identificationCode:");
        String code = scanner.nextLine();
        System.out.println("enter mail address:");
        String mailAddress = scanner.nextLine();
        System.out.println("Want to get push? 1-yes 0-no:");
        String pushOnFromUser = scanner.nextLine();

        boolean success = this.clientController.addNewClient(personId, fName, lName, date, houseNum, houseStreet, city,
                state, creditID, period, code, mailAddress, pushOnFromUser);
        if (success) {
            System.out.println("client added successfully");
        } else {
            System.out.println("Failed to add client");
        }


    }
    public void deleteClient(Scanner scanner) {
        System.out.println("choose Client ID to delete:");
        viewAllClients();

        System.out.print("client ID:");
        String clientID = scanner.nextLine();

        boolean success = this.clientController.deleteClient(clientID);
        if (success) {
            System.out.println("client " + clientID + " delete successfully");
        } else {
            System.out.println("Failed to delete " + clientID);
        }
        System.out.println();


    }
    public void viewAllClients() {
        boolean success = this.clientController.printAllClients();
        if (!success) {
            System.out.println("Failed to viewAllClients ");
        }

        System.out.println();
    }
    public void sendClientBirthdayPush() {
        String message = "מזל טוב ליום הולדתך! הנך מקבל 100 שח מתנה ברשת מסעדות HIT";

        boolean success = this.clientController.sendClientBirthdayPush(message);
        if (success) {
            System.out.println("send Client Birthday push successfully");
        } else {
            System.out.println("Failed to Push Birthday ");
        }
        System.out.println();
    }
    public void sendClientPush(Scanner scanner) {
        System.out.print("Enter message to sent : ");
        String message = scanner.nextLine();

        boolean success = this.clientController.sendClientPush(message);
        if (success) {
            System.out.println("send Client Push successfully");
        } else {
            System.out.println("Failed to Push ");
        }
        System.out.println();


    }
}














