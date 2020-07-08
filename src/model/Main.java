package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.util.*;


public class Main {
    public static boolean isClockOut = false;

    public static void main(String[] args) throws Exception {

        StaffRepository staffRepository = StaffRepositoryImpel.getInstance();
        MenuRepository menuRepository = MenuItemRepositoryImpel.getInstance();
        OrderRepository orderRepository = OrderRepositoryImpel.getInstance();
        HoursReportRepository hoursReportRepository = HoursReportRepositoryImpel.getInstance();
        ClientRepository clientRepository = ClientRepositoryImpel.getInstance();
        Scanner scanner = new Scanner(System.in);
        int shiftNum;

//addNewStaff(scanner,staffRepository);
        while (true) {


            System.out.println("Welcome!");
            System.out.println("Enter userName!");
            String userName = scanner.nextLine();
            System.out.println("Enter password!");
            String password = scanner.nextLine();

            boolean isUserCorrect = false;

            Staff staff = staffRepository.getStaffByUserName(userName);

            if (staff != null)
                isUserCorrect = staff.isPasswordCorrect(password);

            if (isUserCorrect) {
                isClockOut = false;

                shiftNum = hoursReportRepository.clockIn(staff);
                System.out.println("Clock in");

                while (!isClockOut) {
                    if (staff.isManager())
                        managerOptions(scanner, shiftNum, clientRepository, hoursReportRepository, staff, orderRepository, staffRepository, menuRepository);

                    else
                        employeeOptions(scanner, shiftNum, clientRepository, hoursReportRepository, staff, orderRepository, staffRepository, menuRepository);


                }
            } else {
                System.out.println("userName or password dos'nt exist!");
            }

        }

    }

    private static void managerOptions(Scanner scanner, int shiftNum, ClientRepository clientRepository, HoursReportRepository hoursReportRepository, Staff staff, OrderRepository orderRepository, StaffRepository staffRepository, MenuRepository menuRepository) throws Exception {
        System.out.println("");
        //menu item
        System.out.println("1. Add new Menu item");
        System.out.println("2. view all Menu Items");
        System.out.println("3. Edit Menu Item");
        System.out.println("4. delete Menu Item");

        //staff
        System.out.println("5. Add new Staff member");
        System.out.println("6. Edit Staff personal details");
        System.out.println("7. Edit Staff user details");
        System.out.println("8. Delete Staff member");
        System.out.println("9. view all Staff members");
        System.out.println("10. view staff hour wage");
        System.out.println("18. view total staff hours report by month");

        //order
        System.out.println("11. edit order");
        System.out.println("12. add new order");
        System.out.println("13. delete order");
        System.out.println("14. view all order");
        System.out.println("15. close order");
        System.out.println("16. view total orders report (only close)");

        System.out.println("17. clock out");

        System.out.println("19. pay salary to staff id");
        //client:
        System.out.println("20. add new client");
        System.out.println("21. delete client");
        System.out.println("22.update client ");
        System.out.println("23.view all Clients ");
        System.out.println("24.send client push ");
        System.out.println("Q. Exit");

        String selectedOption = scanner.nextLine();
        switch (selectedOption) {
            case MenuCases.ADD_NEW_MENU_ITEM:
                addNewMenuItem(scanner, menuRepository);
                break;

            case MenuCases.VIEW_ALL_MENU_ITEMS_MANAGER:
                viewAllMenuItems(menuRepository);
                break;

            case MenuCases.EDIT_NEW_MENU_ITEM:
                editMenuItem(scanner, menuRepository);
                break;
            case MenuCases.DELETE_NEW_MENU_ITEM:
                deleteMenuItem(scanner, menuRepository);
                break;

            case MenuCases.ADD_NEW_STAFF:
                addNewStaff(scanner, staffRepository);
                break;

            case MenuCases.EDIT_STAFF_PERSONAL_DETAILS:
                editStaffPersonalDetails(scanner, staffRepository);
                break;
            case MenuCases.EDIT_STAFF_USER_DETAILS_DETAILS:
                editStaffUserDetails(scanner, staffRepository);


                break;

            case MenuCases.DELETE_STAFF:
                deleteStaffMember(scanner, staffRepository);
                break;

            case MenuCases.VIEW_ALL_STAFF:
                viewAllStaff(staffRepository);
                break;

            case MenuCases.VIEW_STAFF_HOUR_WAGE_REPORT:
                viewStaffHouerWage(scanner, hoursReportRepository);
                break;

            case MenuCases.EDIT_ORDER:
                editOrderAllList(scanner, staff, orderRepository, menuRepository, clientRepository);
                break;

            case MenuCases.NEW_ORDER:
                addNewOrder(scanner, staff, orderRepository, menuRepository, clientRepository);
                break;

            case MenuCases.DELETE_ORDER:
                deleteOrder(scanner, orderRepository);
                break;

            case MenuCases.VIEW_ALL_ORDERS:
                viewAllOpenOrders(orderRepository);
                break;

            case MenuCases.CLOSE_ORDER:
                closeOrderAllList(scanner, orderRepository);
                break;

            case MenuCases.TOTAL_ORDER_REPORT:
                totalOrderReport(orderRepository);
                break;

            case MenuCases.CLOCK_OUT_MANAGER:
                clockOut(staff, shiftNum, hoursReportRepository);
                break;

            case MenuCases.TOTAL_STAFF_HOURS_REPORT_BY_MONTH:
                viewAllStaffHoursReports(scanner, hoursReportRepository);
                break;

            case MenuCases.ADD_CLIENT:
                addNewClient(scanner, clientRepository);
                break;

            case MenuCases.DELETE_CLIENT:
                deleteClient(scanner, clientRepository);

            case MenuCases.VIEW_CLIENT_LIST:
                viewAllClients(clientRepository);
                break;

            case MenuCases.UPDATE_CLIENT:
                updateClient(scanner, clientRepository);
                break;

            case MenuCases.SEND_CLIENT_PUSH:
                sendClientPush(scanner, clientRepository);
                break;

            case MenuCases.PAY_SALARY:
                paySalaryToStaffID(scanner, staffRepository, hoursReportRepository);
                break;
            case "Q":
            case "q":
            default:
                System.out.println("Goodbye");
                System.exit(0);
        }
    }

    private static void paySalaryToStaffID(Scanner scanner, StaffRepository staffRepository, HoursReportRepository hoursReportRepository) throws Exception {
        System.out.print("choose staff Id from list : ");
        viewAllStaff(staffRepository);
        String staffID = scanner.nextLine();

        System.out.println("Enter month to pay:");
        String month = scanner.nextLine();


        Staff staff = staffRepository.getStaffByID(Integer.parseInt(staffID));

        double totalSalary = getTotalSalary(staff, Integer.parseInt(month), hoursReportRepository);
        String message = "hello " + staff.getFirstName() + " your salary from month " + month + " is " + totalSalary;

        Mail.sendMail(message, staff.getMailAddress());
    }

    private static void sendClientPush(Scanner scanner, ClientRepository clientRepository) throws Exception {
        System.out.print("Enter message to sent : ");
        String message = scanner.nextLine();

        Set<String> mails = new HashSet<String>();

        Set<Client> clients = clientRepository.getAllClientsPushOn();

        for (Client client : clients) {
            mails.add(client.getMailAddress());
        }

        Mail.sendMail(message, mails);
    }

    private static void updateClient(Scanner scanner, ClientRepository clientRepository) throws Exception {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.ENGLISH);

        System.out.println("select client id to edit from list : ");
        viewAllClients(clientRepository);

        String clientID = scanner.nextLine();
        Client client = clientRepository.getClient(Integer.parseInt(clientID));

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

                Address address = new Address(Integer.parseInt(houseNum), houseStreet, city, state);

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

                CreditDetails creditDetails = new CreditDetails(creditID, periodDate, Integer.parseInt(identificationCode));
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

        clientRepository.updateClient(client);

    }

    private static Client getClientFromUser(Scanner scanner) throws Exception {
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

        Address address = new Address(Integer.parseInt(houseNum), houseStreet, city, state);

        System.out.println("Credit details:");
        System.out.println("enter creditID:");
        String creditID = scanner.nextLine();

        System.out.println("enter period in this format (dd/mm/yyyy):");
        String period = scanner.nextLine();
        LocalDate periodDate = LocalDate.parse(period, formatter);

        System.out.println("enter identificationCode:");
        String identificationCode = scanner.nextLine();

        CreditDetails creditDetails = new CreditDetails(creditID, periodDate, Integer.parseInt(identificationCode));

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

    private static void addNewClient(Scanner scanner, ClientRepository clientRepository) throws Exception {

        Client newClient = getClientFromUser(scanner);
        clientRepository.addClient(newClient);

    }

    private static void deleteClient(Scanner scanner, ClientRepository clientRepository) throws Exception {

        System.out.println("choose Client ID to delete:");
        int sizeClients = viewAllClients(clientRepository);

        if (sizeClients != 0) {
            System.out.print("client ID:");
            String clientID = scanner.nextLine();

            clientRepository.deleteClient(Integer.parseInt(clientID));

        } else {
            System.out.print("no client to delete");
        }

    }

    private static int viewAllClients(ClientRepository clientRepository) throws Exception {

        Set<Client> clients = clientRepository.getAllClients();

        for (Client client : clients) {
            System.out.println(client);
        }

        return clients.size();

    }


    private static void employeeOptions(Scanner scanner, int shiftNum, ClientRepository clientRepository, HoursReportRepository hoursReportRepository, Staff staff, OrderRepository orderRepository, StaffRepository staffRepository, MenuRepository menuRepository) throws Exception {
        System.out.println("1. view all Menu Items");
        System.out.println("2. add new order");
        System.out.println("3. edit my order");
        System.out.println("4. delete order");
        System.out.println("5. view my orders");
        System.out.println("6. close my order");
        System.out.println("7. clock out");
        System.out.println("8. view my hours report");
        System.out.println("9. add new client");
        System.out.println("10. delete client");
        System.out.println("11.update client ");
        System.out.println("12.view all Clients ");

        System.out.println("Q. Exit");

        String selectedOption = scanner.nextLine();
        switch (selectedOption) {

            case MenuCases.VIEW_ALL_MENU_ITEMS_EMPLOYEE:
                viewAllMenuItems(menuRepository);
                break;


            case MenuCases.NEW_ORDER_EMPLOYEE:
                addNewOrder(scanner, staff, orderRepository, menuRepository, clientRepository);
                break;

            case MenuCases.EDIT_MY_ORDER:
                editOrderStaffList(scanner, staff, orderRepository, menuRepository, clientRepository);
                break;

            case MenuCases.DELETE_MY_ORDER:
                deleteOrderByStaff(scanner, staff, orderRepository);
                break;

            case MenuCases.VIEW_MY_ORDERS:
                viewAllOpenOrdersByStaff(staff.getPersonId(), orderRepository);
                break;

            case MenuCases.CLOSE_MY_ORDER:
                closeOrderByStaff(scanner, staff, orderRepository);
                break;

            case MenuCases.CLOCK_OUT_EMPLOYEE:
                clockOut(staff, shiftNum, hoursReportRepository);
                break;

            case MenuCases.MY_HOURS_REPORT:
                viewMyHoursReport(staff, hoursReportRepository);
                break;

            case MenuCases.ADD_CLIENT_EMPLOYEE:
                addNewClient(scanner, clientRepository);
                break;

            case MenuCases.DELETE_CLIENT_EMPLOYEE:
                deleteClient(scanner, clientRepository);

            case MenuCases.VIEW_CLIENT_LIST_EMPLOYEE:
                viewAllClients(clientRepository);
                break;

            case MenuCases.UPDATE_CLIENT_EMPLOYEE:
                updateClient(scanner, clientRepository);
                break;
            case "Q":
            case "q":
            default:
                System.out.println("Goodbye");
                System.exit(0);
        }


    }

    private static void viewStaffHouerWage(Scanner scanner, HoursReportRepository hoursReportRepository) throws Exception {
        System.out.println("Enter staff Id:");
        String staffID = scanner.nextLine();

        Set<StaffHour> staffHours = hoursReportRepository.getStaffHourBy(Integer.parseInt(staffID));
        printStaffHourList(staffHours);
    }

    private static void viewAllStaffHoursReports(Scanner scanner, HoursReportRepository hoursReportRepository) throws Exception {
        System.out.println("Enter month to watch:");
        String month = scanner.nextLine();

        Set<StaffHour> staffHours = hoursReportRepository.getAllStaffHour(Integer.parseInt(month));
        printStaffHourList(staffHours);
    }

    private static void printStaffHourList(Set<StaffHour> staffHours) throws Exception {
        int totalHours = 0;
        int totalMin = 0;
        double totalSalary = 0;

        for (StaffHour staffHour : staffHours) {
            System.out.println(staffHour);
            totalHours += getHoursDifference(staffHour.getClockInDate(), staffHour.getClockOutDate());
            totalMin += getMinDifference(staffHour.getClockInDate(), staffHour.getClockOutDate());

            //totalSalary += totalHours * (staffHour.getStaff().getWage());

        }
        System.out.println("total hours: " + totalHours + " Minutes: " + totalMin);
        //System.out.println("total salary: " + totalSalary);
    }

    private static double getTotalSalary(Staff staff, int month, HoursReportRepository hoursReportRepository) throws Exception {
        Set<StaffHour> staffHours = hoursReportRepository.getAllStaffHour(month);

        int totalHours = 0;
        int totalMin = 0;
        double totalSalary = 0;

        for (StaffHour staffHour : staffHours) {
            System.out.println(staffHour);
            totalHours += getHoursDifference(staffHour.getClockInDate(), staffHour.getClockOutDate());
            totalMin += getMinDifference(staffHour.getClockInDate(), staffHour.getClockOutDate());

        }
        System.out.println("total hours: " + totalHours + " Minutes: " + totalMin);
        System.out.println("total salary: " + totalSalary);

        totalSalary = totalHours * staff.getWage() + (totalMin / 60 * staff.getWage());

        return totalSalary;
    }

    private static void viewMyHoursReport(Staff staff, HoursReportRepository hoursReportRepository) throws Exception {

        Set<StaffHour> staffHours = hoursReportRepository.getStaffHourBy(staff.getPersonId());
        printStaffHourList(staffHours);
    }


    private static long getMinDifference(Date date1, Date date2) {

        long diff = date2.getTime() - date1.getTime();

        long diffMinutes = diff / (60 * 1000) % 60;
        long diffHours = diff / (60 * 60 * 1000) % 24;

        return diffMinutes;

    }

    private static long getHoursDifference(Date date1, Date date2) {

        long diff = date2.getTime() - date1.getTime();

        long diffMinutes = diff / (60 * 1000) % 60;
        long diffHours = diff / (60 * 60 * 1000) % 24;

        String hoursMinString = "hours: " + diffHours + "Minutes: " + diffMinutes;

        return diffHours;

    }

    private static void clockOut(Staff staff, int shiftNum, HoursReportRepository hoursReportRepository) throws Exception {

        hoursReportRepository.clockOut(staff, shiftNum);
        isClockOut = true;
        System.out.println("Clocked out");

    }

    private static void totalOrderReport(OrderRepository orderRepository) throws Exception {

        Set<Order> orders = orderRepository.getAllClosedOrders();
        double sumReport = 0;

        for (Order o : orders) {
            System.out.println(o);
            sumReport += o.getTotalOrderPrice();

        }
        System.out.println("total orders price:" + sumReport);

    }

    private static void closeOrderAllList(Scanner scanner, OrderRepository orderRepository) throws Exception {
        System.out.println("choose order ID to close from list:  ");
        viewAllOpenOrders(orderRepository);

        closeOrder(scanner, orderRepository);
    }


    private static void closeOrderByStaff(Scanner scanner, Staff staff, OrderRepository orderRepository) throws Exception {
        System.out.println("choose order ID to close from list:  ");
        viewAllOpenOrdersByStaff(staff.getPersonId(), orderRepository);

        closeOrder(scanner, orderRepository);
    }

    private static void closeOrder(Scanner scanner, OrderRepository orderRepository) throws Exception {
        System.out.print("order ID: ");
        String orderID = scanner.nextLine();

        Order orderToClose = orderRepository.getOrder(Integer.parseInt(orderID));
        System.out.println("Total Order price: " + orderToClose.getTotalOrderPrice());

        System.out.println("press 1 to close , 0 to cancel:  ");
        String toClose = scanner.nextLine();

        if (Integer.parseInt(toClose) == 1) {
            orderRepository.closeOrder(orderToClose);
            System.out.println("Order Closed Successfully");
        } else
            System.out.println("Canceled");

    }

    private static void deleteOrder(Scanner scanner, OrderRepository orderRepository) throws Exception {
        System.out.println("choose order ID to delete:");

        int sizeOrder = viewAllOpenOrders(orderRepository);

        if (sizeOrder != 0) {
            System.out.print("order ID:");
            String orderID = scanner.nextLine();

            orderRepository.deleteOrder(Integer.parseInt(orderID));

        } else {
            System.out.print("no order to delete");
        }
    }

    private static void deleteOrderByStaff(Scanner scanner, Staff staff, OrderRepository orderRepository) throws Exception {
        System.out.println("choose order ID to delete:");

        int sizeOrder = viewAllOpenOrdersByStaff(staff.getPersonId(), orderRepository);

        if (sizeOrder != 0) {
            System.out.print("order ID:");
            String orderID = scanner.nextLine();

            orderRepository.deleteOrder(Integer.parseInt(orderID));

        } else {
            System.out.print("no order to delete");
        }
    }

    private static void editOrderStaffList(Scanner scanner, Staff staff, OrderRepository orderRepository, MenuRepository menuRepository, ClientRepository clientRepository) throws Exception {
        System.out.println("choose order ID from list to edit:  ");
        viewAllOpenOrdersByStaff(staff.getPersonId(), orderRepository);
        String orderID = scanner.nextLine();

        editOrder(orderID, scanner, orderRepository, menuRepository, clientRepository);

    }


    private static void editOrder(String orderID, Scanner scanner, OrderRepository orderRepository, MenuRepository menuRepository, ClientRepository clientRepository) throws Exception {
        Order editOrder = orderRepository.getOrder(Integer.parseInt(orderID));
        System.out.println("choose what you want to edit: ");
        System.out.println("1. menu items");
        System.out.println("2. client");

        String selectedOption = scanner.nextLine();
        switch (selectedOption) {

            case "1":
                Set<MenuItemOrder> menuItems = getMenuItemsFromUser(scanner, menuRepository);
                editOrder.setMenuItems(menuItems);

                break;

            case "2":
                Client client = getClientByClientIDFromUser(scanner, clientRepository);
                editOrder.setClient(client);
                break;

        }

        orderRepository.updateOrder(editOrder);
    }


    private static void editOrderAllList(Scanner scanner, Staff staff, OrderRepository orderRepository, MenuRepository menuRepository, ClientRepository clientRepository) throws Exception {
        System.out.println("choose order ID from list to edit:  ");
        viewAllOpenOrders(orderRepository);
        String orderID = scanner.nextLine();

        editOrder(orderID, scanner, orderRepository, menuRepository, clientRepository);
    }

    private static int viewAllOpenOrdersByStaff(int staffID, OrderRepository orderRepository) throws Exception {
        Set<Order> orders = orderRepository.getAllStaffOpenOrders(staffID);

        for (Order o : orders) {
            System.out.println(o);
        }

        return orders.size();
    }

    private static int viewAllOpenOrders(OrderRepository orderRepository) throws Exception {
        Set<Order> orders = orderRepository.getAllOpenOrders();

        for (Order o : orders) {
            System.out.println(o);
        }

        return orders.size();
    }

    private static Order getOrderFromUser(Scanner scanner, Staff staff, ClientRepository clientRepository, MenuRepository menuRepository) throws Exception {

        int staffId = staff.getPersonId();

        Set<MenuItemOrder> menuItems = getMenuItemsFromUser(scanner, menuRepository);
        Client client = getClientByClientIDFromUser(scanner, clientRepository);

        Order orderFromUser = new Order(staffId, menuItems, client);

        return orderFromUser;
    }

    private static Client getClientByClientIDFromUser(Scanner scanner, ClientRepository clientRepository) throws Exception {

        System.out.println("Is there a customer club? press 1-yes , 0-no");
        String customerClub = scanner.nextLine();

        Client client = null;
        if (customerClub.equals("1")) {
            System.out.println("enter client ID");
            String clientID = scanner.nextLine();
            client = clientRepository.getClient(Integer.parseInt(clientID));
        }

        return client;

    }

    private static Set<MenuItemOrder> getMenuItemsFromUser(Scanner scanner, MenuRepository menuRepository) throws Exception {
        System.out.println("choose menu items ID from list : enter -1 to finish ");
        viewAllMenuItems(menuRepository);

        int indexMenuItem = 1;
        Set<MenuItemOrder> menuItems = new HashSet<MenuItemOrder>();

        System.out.print("menu item " + indexMenuItem++ + ".");
        String menuItemID = scanner.nextLine();

        System.out.print("enter number of item: ");
        String numberOfItem = scanner.nextLine();

        while (!menuItemID.equals("-1")) {
            MenuItem menuItem = menuRepository.find(Integer.parseInt(menuItemID));
            MenuItemOrder menuItemOrder = new MenuItemOrder(menuItem, Integer.parseInt(numberOfItem));
            menuItems.add(menuItemOrder);

            System.out.print("menu item " + indexMenuItem++ + ".");
            menuItemID = scanner.nextLine();

            if (!menuItemID.equals("-1")) {
                System.out.print("enter number of item: ");
                numberOfItem = scanner.nextLine();
            }
        }

        return menuItems;
    }

    private static void addNewOrder(Scanner scanner, Staff staff, OrderRepository orderRepository, MenuRepository menuRepository, ClientRepository clientRepository) throws Exception {

        Order newOrder = getOrderFromUser(scanner, staff, clientRepository, menuRepository);
        orderRepository.addOrder(newOrder);

    }


    private static void viewAllStaff(StaffRepository staffRepository) {
        Set<Staff> staffSet = staffRepository.findAll();
        for (Staff s : staffSet) {


            System.out.println(s);

        }
    }

    private static void addNewStaff(Scanner scanner, StaffRepository staffRepository) throws Exception {
        System.out.print("Enter staff id : ");
        String staffId = scanner.nextLine();
        System.out.print("Entr first name : ");
        String fName = scanner.nextLine();
        System.out.print("Enter last name : ");
        String lName = scanner.nextLine();
        System.out.print("Enter mail address : ");
        String mailAddress = scanner.nextLine();
        System.out.print("Enter birth date in this format (dd/mm/yyyy) :");
        String d = scanner.nextLine();

        System.out.print("Enter private house number:");
        String houseNum = scanner.nextLine();
        System.out.print("Enter house street:");
        String houseStreet = scanner.nextLine();
        System.out.print("Enter city:");
        String city = scanner.nextLine();
        System.out.print("Enter state:");
        String state = scanner.nextLine();
        System.out.print("Enter role (manager/employee:");
        String role = scanner.nextLine();
        System.out.print("Enter User Name:");
        String username = scanner.nextLine();
        System.out.print("Enter password:");
        String staffPassword = scanner.nextLine();

        System.out.println("BankDetails:");
        System.out.println("enter bank Account Number:");
        String bankAccountNumber = scanner.nextLine();

        System.out.println("brunch Number:");
        String brunchNumber = scanner.nextLine();
        //parsing to date format

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.ENGLISH);
        LocalDate date = LocalDate.parse(d, formatter);

        Address address = new Address(Integer.parseInt(houseNum), houseStreet, city, state);
        UserDetails userDetails = new UserDetails(username, staffPassword, Role.valueOf(role));
        BankDetails bankDetails = new BankDetails(bankAccountNumber, Integer.parseInt(brunchNumber));

        if (role.equals("manager")) {
            staffRepository.addStaff(new Manager(Integer.parseInt(staffId), fName, lName, date, address, userDetails, bankDetails, mailAddress));

        }
        if (role.equals("employee")) {
            staffRepository.addStaff(new Employee(Integer.parseInt(staffId), fName, lName, date, address, userDetails, bankDetails, mailAddress));
        }

    }

    private static void deleteStaffMember(Scanner scanner, StaffRepository staffRepository) throws Exception {
        System.out.print("Enter Staff id you want to remove (number): ");
        String id = scanner.nextLine();
        staffRepository.deleteStaff(Integer.parseInt(id));

    }

    private static void editStaffPersonalDetails(Scanner scanner, StaffRepository staffRepository) throws Exception {


        System.out.print("Enter Staff id you want to edit (number): ");

        String staffID = scanner.nextLine();

        if (staffRepository.isExist(Integer.parseInt(staffID)) == false) {
            throw new Exception("Staff does not exists!");

        } else {
            Staff staff = staffRepository.getStaffByID(Integer.parseInt(staffID));

            System.out.print("Entr first name : ");
            String fName = scanner.nextLine();
            staff.setFirstName(fName);

            System.out.print("Enter last name : ");
            String lName = scanner.nextLine();
            staff.setFirstName(lName);

            System.out.print("Enter birth date in this format (dd/mm/yyyy) :");
            String d = scanner.nextLine();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.ENGLISH);
            LocalDate date = LocalDate.parse(d, formatter);

            staff.setBirthDate(date);

            System.out.print("Enter private house number:");
            String houseNum = scanner.nextLine();
            System.out.print("Enter house street:");
            String houseStreet = scanner.nextLine();
            System.out.print("Enter city:");
            String city = scanner.nextLine();
            System.out.print("Enter state:");
            String state = scanner.nextLine();
            Address newAddress = new Address(Integer.parseInt(houseNum), houseStreet, city, state);
            staff.setAddress(newAddress);

            staffRepository.editPersonDetails(staff);

        }


    }

    private static void editStaffUserDetails(Scanner scanner, StaffRepository staffRepository) throws Exception {
        System.out.print("Enter Staff id you want to edit (number): ");

        String staffID = scanner.nextLine();

        if (staffRepository.isExist(Integer.parseInt(staffID)) == false) {
            throw new Exception("Staff does not exists!");
        } else {
            System.out.print("Enter role (manager/employee):");
            String role = scanner.nextLine();

            if (role.equals("manager"))

                System.out.print("Enter User Name:");
            String username = scanner.nextLine();
            System.out.print("Enter password:");
            String staffPassword = scanner.nextLine();

            UserDetails newUseretails = new UserDetails(username, staffPassword, Role.valueOf(role));
            staffRepository.editStaffDetails(Integer.parseInt(staffID), newUseretails);


        }


    }


    private static void editMenuItem(Scanner scanner, MenuRepository menuRepository) throws Exception {
        System.out.print("Write menu item id you want to edit (number): ");
        String id = scanner.nextLine();

        System.out.print("Enter menu item name: ");
        String name = scanner.nextLine();
        System.out.print("enter menu item price: ");
        String price = scanner.nextLine();
        System.out.print("enter menu item type(MAIN,DRINK,ALCOHOL,DESERT:");
        String type = scanner.nextLine();
        menuRepository.updateMenuItem(new MenuItem(Integer.parseInt(id), name, Double.parseDouble(price), MenuItemType.valueOf(type)));

    }

    private static void viewAllMenuItems(MenuRepository menuRepository) throws Exception {
        Set<MenuItem> menu = menuRepository.findAll();
        if (menu == null) {
            throw new Exception("menu is empty exists!");

        }
        for (MenuItem items : menu) {
            System.out.println(items);
        }
    }

    private static void addNewMenuItem(Scanner scanner, MenuRepository menuRepository) throws Exception {
        System.out.print("Write menu item id (number): ");
        String id = scanner.nextLine();
        System.out.print("Entr menu item name: ");
        String name = scanner.nextLine();
        System.out.print("enter menu item price: ");
        String price = scanner.nextLine();
        System.out.print("enter menu item type(MAIN,DRINK,ALCOHOL,DESERT:");
        String type = scanner.nextLine();

        menuRepository.addMenuItem(new MenuItem(Integer.parseInt(id), name, Double.parseDouble(price), MenuItemType.valueOf(type)));
    }

    private static void deleteMenuItem(Scanner scanner, MenuRepository menuRepository) throws Exception {
        System.out.print("Enter menu item id you want to remove (number): ");
        String id = scanner.nextLine();
        menuRepository.deleteMenuItem(Integer.parseInt(id));

    }


}
