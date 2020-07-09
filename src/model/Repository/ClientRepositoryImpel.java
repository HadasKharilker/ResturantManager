package model.Repository;

import model.Client;
import model.FileManager;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class ClientRepositoryImpel implements ClientRepository {   //for singelton
    private static ClientRepositoryImpel INSTANCE;
    private static Object lockObject = new Object();

    private final String FILENAME = "clients";
    private Set<Client> clients;
    private FileManager<Client> fileManager;

    //singelton has a private constructor
    private ClientRepositoryImpel() throws IOException, ClassNotFoundException {
        this.fileManager = new FileManager<Client>(FILENAME);
        this.clients = this.fileManager.read();
    }

    //for singelton use
    public static ClientRepositoryImpel getInstance() throws Exception {
        if (INSTANCE == null) {
            synchronized (lockObject) {
                if (INSTANCE == null) {
                    INSTANCE = new ClientRepositoryImpel();
                }
            }
        }
        return INSTANCE;
    }

    @Override
    public void addClient(Client client) throws Exception {
        if (client == null) {
            throw new Exception("must have a value");
        }

        this.clients.add(client);
        this.fileManager.write(this.clients);
    }

    @Override
    public void deleteClient(int clientID) throws IOException {
        Client client = getClient(clientID);
        clients.remove(client);
        this.fileManager.write(this.clients);
    }

    @Override
    public Client getClient(int clientID) {
        for (Client client : clients) {
            if (client.getPersonId() == clientID) {
                return client;
            }

        }
        return null;
    }

    @Override
    public Set<Client> getAllClients() {
        return this.clients;
    }

    @Override
    public Set<Client> getAllClientsPushOn() {
        Set<Client> clientsPushOn=new HashSet<Client>();

        for(Client client :this.clients){
            if(client.isPushOn())
                clientsPushOn.add(client);
        }
        return clientsPushOn;
    }

    @Override
    public void updateClient(Client client) throws Exception {
        if (client == null) {
            throw new Exception("must have a value");
        }
        if (!(this.clients.contains(client))) {
            throw new Exception("Item does not exists!");
        } else {
            for (Client c : clients) {
                if (c.getPersonId() == client.getPersonId()) {
                    deleteClient(client.getPersonId());
                    addClient(client);
                    break;

                }
            }

        }
        this.fileManager.write(this.clients);
    }


}
