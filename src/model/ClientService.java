package model;

import java.util.Set;

public class ClientService {

    private final ClientRepository clientRepository;


    public ClientService() throws Exception {
        this.clientRepository = ClientRepositoryImpel.getInstance();
    }

    public Client getClient(int clientID) {
        try {
            return this.clientRepository.getClient(clientID);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public boolean addNewClient(Client client) {
        try {
            this.clientRepository.addClient(client);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    public boolean deleteClient(int clientID) {
        try {
            this.clientRepository.deleteClient(clientID);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    public Set<Client> getAllClients() {
        try {
            return this.clientRepository.getAllClients();
        } catch (Exception e) {
            e.printStackTrace();

            return null;
        }

    }

    public boolean updateClient(Client client) {
        try {
            this.clientRepository.updateClient(client);
            return true;
        } catch (Exception e) {
            e.printStackTrace();

            return false;
        }

    }

    public Set<Client> getAllClientsPushOn() {
        try {
            return this.clientRepository.getAllClientsPushOn();

        } catch (Exception e) {
            e.printStackTrace();

            return null;
        }

    }

}
