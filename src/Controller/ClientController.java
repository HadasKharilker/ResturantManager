package Controller;

import model.*;
import model.Service.ClientService;

import java.util.HashSet;
import java.util.Set;

public class ClientController {

    //for singelton
    private static ClientController INSTANCE;
    private static final Object lockObject = new Object();
    private final ClientService clientService;


    //for singelton
    private ClientController() throws Exception {
        this.clientService = new ClientService();
    }

    public static ClientController getInstance() throws Exception {
        if (INSTANCE == null) {
            synchronized (lockObject) {
                if (INSTANCE == null) {
                    INSTANCE = new ClientController();
                }
            }
        }

        return INSTANCE;
    }


    public Client getClient(int clientID) {
        return clientService.getClient(clientID);

    }


    public boolean addNewClient(String personId, String fName, String lname, String date, String houseNum, String houseStreet, String city,
                                String state, String creditID, String period, String code, String mailAddress, String pushOnFromUser) {

        return clientService.addNewClient(personId, fName, lname, date, houseNum, houseStreet, city,
                state, creditID, period, code, mailAddress, pushOnFromUser);
    }

    public boolean deleteClient(String clientID) {
        if (clientID != "")
            return clientService.deleteClient(clientID);

        return false;
    }

    public boolean printAllClients() {
        return clientService.printAllClients();
    }

    public boolean sendClientBirthdayPush(String message) {
        return clientService.sendClientBirthdayPush(message);
    }

    public boolean sendClientPush(String message) {
        return clientService.sendClientPush(message);
    }

    public boolean editClientName(String clientID, String firstName, String lastName) {
        return clientService.editClientName(clientID, firstName, lastName);
    }

    public boolean editBdayClient(String clientID, String bDay) {
        return clientService.editBdayClient(clientID, bDay);
    }

    public boolean editAddress(String clientID, String houseStreet, String houseNum, String city, String state) {
        return clientService.editAddress(clientID, houseStreet, houseNum, city, state);
    }

    public boolean editCreditCard(String clientID, String creditID, String period, String identificationCode) {
        return clientService.editCreditCard(clientID, creditID, period, identificationCode);
    }

    public boolean editMailAddress(String clientID, String mail) {
        return clientService.editMail(clientID, mail);
    }

    public boolean editPushOn(String clientID, String pushOn) {
        return clientService.editPushOn(clientID, pushOn);
    }


}
