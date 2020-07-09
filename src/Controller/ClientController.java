package Controller;

import model.*;
import model.Service.ClientService;

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

    public Set<Client> getAllClients() {
        return clientService.getAllClients();

    }

    public Set<Client> getAllClientsPushOn() {
        return clientService.getAllClientsPushOn();

    }

    public boolean addNewClient(Client client) {
        return clientService.addNewClient(client);
    }

    public boolean deleteClient(int clientID) {
        return clientService.deleteClient(clientID);
    }


    public boolean updateClient(Client client) {
        return clientService.updateClient(client);
    }


}
