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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.ENGLISH);

        System.out.println("select client id to edit from list : ");
        viewAllClients();

        String clientID = scanner.nextLine();
        Client client = this.clientController.getClient(Integer.parseInt(clientID));

        System.out.println("choose what you want to edit: ");
        System.out.println("1. name");
        System.out.println("2. birth date");
        System.out.println("3. address");
        System.out.println("4. Credit details");
        System.out.println("5. mail address");
        System.out.println("6. notifications");

        String selectedOption = scanner.nextLine();
        switch (selectedOption) {

            case "1":
                System.out.println("enter first name");
                String firstName = scanner.nextLine();
                client.setFirstName(firstName);

                System.out.println("enter last name");
                String lastName = scanner.nextLine();
                client.setLastName(lastName);
                break;

            case "2":
                System.out.print("Enter birth date in this format (dd/mm/yyyy) :");
                String d = scanner.nextLine();
                LocalDate date = LocalDate.parse(d, formatter);

                client.setBirthDate(date);
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

                Address address = new Address.AddressBuilder(houseStreet).houseNumber(Integer.parseInt(houseNum)).city(city).state(state).build();


                client.setAddress(address);
                break;

            case "4":
                System.out.println("Credit details:");
                System.out.println("enter creditID:");
                String creditID = scanner.nextLine();

                System.out.println("enter period in this format (dd/mm/yyyy):");
                String period = scanner.nextLine();
                LocalDate periodDate = LocalDate.parse(period, formatter);

                System.out.println("enter identificationCode:");
                String identificationCode = scanner.nextLine();

                CreditDetails creditDetails = new CreditDetails.CreditDetailsBuilder(creditID).period(periodDate).code(Integer.parseInt(identificationCode)).build();


                client.setCreditDetails(creditDetails);
                break;

            case "5":
                System.out.println("enter mail address:");
                String mailAddress = scanner.nextLine();
                client.setMailAddress(mailAddress);
                break;

            case "6":
                System.out.println("Want to get push? 1-yes 0-no:");
                String pushOnFromUser = scanner.nextLine();
                Boolean pushOn = false;

                if (pushOnFromUser.equals("1"))
                    pushOn = true;

                client.setPushOn(pushOn);
                break;

        }

        this.clientController.updateClient(client);

    }


    public void addNewClient(Scanner scanner) throws Exception {

        Client newClient = getClientFromUser(scanner);
        this.clientController.addNewClient(newClient);

    }

    private Client getClientFromUser(Scanner scanner) throws Exception {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.ENGLISH);

        System.out.print("Enter person id : ");
        String personId = scanner.nextLine();
        System.out.print("Enter first name : ");
        String fName = scanner.nextLine();
        System.out.print("Enter last name : ");
        String lName = scanner.nextLine();

        System.out.print("Enter birth date in this format (dd/mm/yyyy) :");
        String d = scanner.nextLine();
        LocalDate date = LocalDate.parse(d, formatter);

        System.out.print("Enter private house number:");
        String houseNum = scanner.nextLine();
        System.out.print("Enter house street:");
        String houseStreet = scanner.nextLine();
        System.out.print("Enter city:");
        String city = scanner.nextLine();
        System.out.print("Enter state:");
        String state = scanner.nextLine();

        Address address = new Address.AddressBuilder(houseStreet).houseNumber(Integer.parseInt(houseNum)).city(city).state(state).build();


        System.out.println("Credit details:");
        System.out.println("enter creditID:");
        String creditID = scanner.nextLine();

        System.out.println("enter period in this format (dd/mm/yyyy):");
        String period = scanner.nextLine();
        LocalDate periodDate = LocalDate.parse(period, formatter);

        System.out.println("enter identificationCode:");
        String identificationCode = scanner.nextLine();

        CreditDetails creditDetails = new CreditDetails.CreditDetailsBuilder(creditID).period(periodDate).code(Integer.parseInt(identificationCode)).build();


        System.out.println("enter mail address:");
        String mailAddress = scanner.nextLine();

        System.out.println("Want to get push? 1-yes 0-no:");
        String pushOnFromUser = scanner.nextLine();
        Boolean pushOn = false;

        if (pushOnFromUser.equals("1"))
            pushOn = true;


        Client clientFromUser = new Client(Integer.parseInt(personId), fName, lName, date, address, mailAddress, creditDetails, pushOn);

        return clientFromUser;
    }

    public void deleteClient(Scanner scanner) throws Exception {

        System.out.println("choose Client ID to delete:");
        int sizeClients = viewAllClients();

        if (sizeClients != 0) {
            System.out.print("client ID:");
            String clientID = scanner.nextLine();

            clientController.deleteClient(Integer.parseInt(clientID));

        } else {
            System.out.print("no client to delete");
        }

    }

    public int viewAllClients() throws Exception {

        Set<Client> clients = this.clientController.getAllClients();

        for (Client client : clients) {
            System.out.println(client);
        }

        return clients.size();

    }


    public void sendClientPush(Scanner scanner) throws Exception {
        System.out.print("Enter message to sent : ");
        String message = scanner.nextLine();

        Set<String> mails = new HashSet<String>();

        Set<Client> clients = clientController.getAllClientsPushOn();

        if (clients != null) {
            for (Client client : clients) {
                mails.add(client.getMailAddress());
            }

            Mail.sendMail(message, mails);
        }
    }

    public void sendClientBirthdayPush(Scanner scanner) throws Exception {

        String message = "מזל טוב ליום הולדתך! הנך מקבל 100 שח מתנה ברשת מסעדות HIT" ;

        Set<String> mails = new HashSet<String>();

        Set<Client> clients = clientController.getAllClientsBirthday();

        if (clients != null && clients.size() != 0) {
            for (Client client : clients) {
                mails.add(client.getMailAddress());
            }

            Mail.sendMail(message, mails);
        }
    }


}














