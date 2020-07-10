package model.Service;

import model.Address;
import model.Client;
import model.CreditDetails;
import model.Mail;
import model.Repository.ClientRepository;
import model.Repository.ClientRepositoryImpel;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Locale;
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

    public boolean addNewClient(String personId, String fName, String lName, String date, String houseNum, String houseStreet, String city,
                                String state, String creditID, String period, String code, String mailAddress, String pushOnFromUser) {
        try {

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.ENGLISH);
            LocalDate localDate = LocalDate.parse(date, formatter);

            Address address = new Address.AddressBuilder(houseStreet).houseNumber(Integer.parseInt(houseNum)).city(city).state(state).build();
            CreditDetails creditDetails = new CreditDetails.CreditDetailsBuilder(creditID).period(LocalDate.parse(period, formatter)).code(Integer.parseInt(code)).build();

            Boolean pushOn = false;
            if (pushOnFromUser.equals("1"))
                pushOn = true;

            Client client = new Client(Integer.parseInt(personId), fName, lName, localDate, address, mailAddress, creditDetails, pushOn);

            this.clientRepository.addClient(client);

            return true;


        } catch (Exception e) {

            e.printStackTrace();
            return false;
        }

    }

    public boolean deleteClient(String clientID) {
        try {
            this.clientRepository.deleteClient(Integer.parseInt(clientID));
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

    public Set<Client> getAllClientsPushOn() {
        try {
            return this.clientRepository.getAllClientsPushOn();

        } catch (Exception e) {
            e.printStackTrace();

            return null;
        }

    }

    public Set<Client> getAllClientsBirthday() {
        try {
            return this.clientRepository.getAllClientsBirthday();

        } catch (Exception e) {
            e.printStackTrace();

            return null;
        }

    }

    public boolean printAllClients() {
        try {
            Set<Client> clients = getAllClients();

            for (Client client : clients) {
                System.out.println(client);
            }

            return true;
        } catch (Exception ex) {
            System.out.println("Failed to view All Clients");

            return false;
        }
    }

    public boolean sendClientBirthdayPush(String message) {
        try {

            Set<String> mails = new HashSet<String>();

            Set<Client> clients = getAllClientsBirthday();

            if (clients != null && clients.size() != 0) {
                for (Client client : clients) {
                    mails.add(client.getMailAddress());
                }

                Mail.sendMail(message, mails);
            }
            return true;

        } catch (Exception ex) {
            return false;
        }
    }

    public boolean sendClientPush(String message) {
        try {
            Set<String> mails = new HashSet<String>();

            Set<Client> clients = getAllClientsPushOn();

            if (clients != null) {
                for (Client client : clients) {
                    mails.add(client.getMailAddress());
                }

                Mail.sendMail(message, mails);
            }
            return true;

        } catch (Exception ex) {
            return false;
        }
    }

    public boolean editClientName(String clientID, String firstName, String lastName) {
        try {
            Client client = getClient(Integer.parseInt(clientID));
            client.setFirstName(firstName);
            client.setLastName(lastName);

            this.clientRepository.updateClient(client);
            return true;

        } catch (Exception ex) {
            return false;
        }
    }

    public boolean editBdayClient(String clientID, String bDay) {
        try {

            Client client = getClient(Integer.parseInt(clientID));

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.ENGLISH);
            LocalDate date = LocalDate.parse(bDay, formatter);
            client.setBirthDate(date);

            this.clientRepository.updateClient(client);
            return true;

        } catch (Exception ex) {
            return false;
        }
    }

    public boolean editAddress(String clientID, String houseStreet, String houseNum, String city, String state) {
        try {

            Client client = getClient(Integer.parseInt(clientID));
            Address address = new Address.AddressBuilder(houseStreet).houseNumber(Integer.parseInt(houseNum)).city(city).state(state).build();
            client.setAddress(address);

            this.clientRepository.updateClient(client);
            return true;

        } catch (Exception ex) {
            return false;
        }
    }

    public boolean editCreditCard(String clientID, String creditID, String period, String identificationCode) {
        try {

            Client client = getClient(Integer.parseInt(clientID));
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.ENGLISH);
            LocalDate periodDate = LocalDate.parse(period, formatter);

            CreditDetails creditDetails = new CreditDetails.CreditDetailsBuilder(creditID).period(periodDate).code(Integer.parseInt(identificationCode)).build();
            client.setCreditDetails(creditDetails);

            this.clientRepository.updateClient(client);
            return true;

        } catch (Exception ex) {
            return false;
        }
    }

    public boolean editMail(String clientID, String mail) {
        try {

            Client client = getClient(Integer.parseInt(clientID));
            client.setMailAddress(mail);

            this.clientRepository.updateClient(client);
            return true;

        } catch (Exception ex) {
            return false;
        }
    }

    public boolean editPushOn(String clientID, String pushOn) {
        try {

            Client client = getClient(Integer.parseInt(clientID));

            Boolean pushOnBool = false;

            if (pushOn.equals("1"))
                pushOnBool = true;

            client.setPushOn(pushOnBool);

            this.clientRepository.updateClient(client);
            return true;

        } catch (Exception ex) {
            return false;
        }
    }


}
