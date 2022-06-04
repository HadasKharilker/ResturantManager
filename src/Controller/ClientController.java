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

    public boolean addNewClient(String personId, String fName, String lname, String date, String houseNum, String houseStreet, String city,
                                String state, String creditID, String period, String code, String mailAddress, String pushOnFromUser) {

        if (personId != "")
            return clientService.addNewClient(personId, fName, lname, date, houseNum, houseStreet, city,
                    state, creditID, period, code, mailAddress, pushOnFromUser);

        return false;
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

        if (message != "")
            return clientService.sendClientBirthdayPush(message);

        return false;
    }

    public boolean sendClientPush(String message) {

        if (message != "")
            return clientService.sendClientPush(message);

        return false;
    }

    public boolean editClientName(String clientID, String firstName, String lastName) {
        if (clientID != "")
            return clientService.editClientName(clientID, firstName, lastName);

        return false;
    }

    public boolean editBdayClient(String clientID, String bDay) {
        if (clientID != "")
            return clientService.editBdayClient(clientID, bDay);

        return false;
    }

    public boolean editAddress(String clientID, String houseStreet, String houseNum, String city, String state) {
        if (clientID != "")
            return clientService.editAddress(clientID, houseStreet, houseNum, city, state);

        return false;
    }

    public boolean editCreditCard(String clientID, String creditID, String period, String identificationCode) {
        if (clientID != "")
            return clientService.editCreditCard(clientID, creditID, period, identificationCode);
        return false;
    }

    public boolean editMailAddress(String clientID, String mail) {
        if (clientID != "")
            return clientService.editMail(clientID, mail);

        return false;
    }

    public boolean editPushOn(String clientID, String pushOn) {
        if (clientID != "")
            return clientService.editPushOn(clientID, pushOn);


        return false;
    }


}
