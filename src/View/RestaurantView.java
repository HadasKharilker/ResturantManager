package View;


import java.util.Scanner;


import Controller.*;
import model.*;

public class RestaurantView {


    private boolean isClockOut = false;
    private int shiftNum;

    private final LoginController loginController;

    //Menu View
    private final MenuController menuController;
    private final MenuView menuView;


    //Staff View
    private final StaffController staffController;
    private final StaffView staffView;


    //Order
    private final OrderController orderController;
    private final OrderView orderView;


    //Client
    private final ClientController clientController;
    private final ClientView clientView;

    //hour report
    private final HoursReportController hoursReportController;
    private final HoursReportView hoursReportView;


    public RestaurantView() throws Exception {

        this.menuView = new MenuView();
        this.staffView = new StaffView();
        this.orderView = new OrderView();
        this.clientView = new ClientView();
        this.hoursReportView = new HoursReportView();
        this.menuController = MenuController.getInstance();
        this.staffController = StaffController.getInstance();
        this.orderController = OrderController.getInstance();
        this.clientController = ClientController.getInstance();
        this.hoursReportController = HoursReportController.getInstance();
        this.loginController = LoginController.getInstance();

    }

    public void start(RestaurantView resturantView) throws Exception {

        System.out.println("Welcome to the Resturant Manager - The Right Way For Your Business");
        boolean stayInResturant = true;

        try (Scanner scanner = new Scanner(System.in)) {

             // staffView.addNewStaff(scanner);
            while (stayInResturant) {
                System.out.println("1. login");
                System.out.println("Q. exit");

                String command = scanner.nextLine();
                switch (command) {
                    case "1":
                        this.login(scanner, resturantView);
                        break;
                    case "q":
                    case "Q":
                        stayInResturant = false;
                        break;
                    default:
                        System.out.println("Selection is not valid, Please select a option from the available options");
                }
            }
        }

    }

    public void login(Scanner scanner, RestaurantView resturantView) throws Exception {


        System.out.print("username: ");
        String username = scanner.nextLine();
        System.out.print("password: ");
        String password = scanner.nextLine();

        boolean login = loginController.login(username, password);
        if (login) {
            Staff staff = loginController.getStaffByUserName(username);
            isClockOut = false;
            shiftNum = hoursReportView.clockIn(staff);

            if (staff.isManager()) {
                System.out.println("You are now logged in as manager");
                resturantView.manager(staff, scanner);
            } else if (staff.isShiftManager()) {
                System.out.println("You are now logged in as ShiftManager");
                resturantView.shiftManager(staff, scanner);

            } else if (staff.isMinorWorker()) {
                System.out.println("You are now logged in as MinorWorker");
                resturantView.employee(staff, scanner);
            }
        } else
            System.out.println("User name or password is wrong");


    }


    public void employee(Staff staff, Scanner scanner) throws Exception {

        boolean stayInEmployee = true;

        while (stayInEmployee && !isClockOut) {
            System.out.println();
            System.out.println("Menu item:");
            System.out.println("1. view all Menu Items");
            System.out.println();
            System.out.println("Orders:");
            System.out.println("2. add new order");
            System.out.println("3. edit my order");
            System.out.println("4. delete order");
            System.out.println("5. view my open orders");
            System.out.println("6. close my order");
            System.out.println();
            System.out.println("Clients:");
            System.out.println("7. add new client");
            System.out.println("8. delete client");
            System.out.println("9.update client ");
            System.out.println("10.view all Clients ");
            System.out.println();
            System.out.println("Others:");
            System.out.println("11. view my hours report");
            System.out.println("12. clock out");
            System.out.println("Q. Exit");

            String selectedOption = scanner.nextLine();
            switch (selectedOption) {

                case MenuCases.VIEW_ALL_MENU_ITEMS_EMPLOYEE:
                    menuView.viewAllMenuItems();
                    break;


                case MenuCases.NEW_ORDER_EMPLOYEE:
                    this.orderView.addNewOrder(scanner, staff, menuView, menuController, clientController);
                    break;

                case MenuCases.EDIT_MY_ORDER:
                    this.orderView.editOrderStaffList(scanner, staff, menuView, menuController);
                    break;

                case MenuCases.DELETE_MY_ORDER:
                    this.orderView.deleteOrderByStaff(scanner, staff);
                    break;

                case MenuCases.VIEW_MY_ORDERS:
                    this.orderView.viewAllOpenOrdersByStaff(staff);
                    break;

                case MenuCases.CLOSE_MY_ORDER:
                    this.orderView.closeOrderByStaff(scanner, staff);
                    break;

                case MenuCases.CLOCK_OUT_EMPLOYEE:
                    this.hoursReportView.clockOut(staff, shiftNum);
                    isClockOut = true;
                    break;

                case MenuCases.MY_HOURS_REPORT:
                    this.hoursReportView.viewMyHoursReport(staff);
                    break;

                case MenuCases.ADD_CLIENT_EMPLOYEE:
                    this.clientView.addNewClient(scanner);
                    break;

                case MenuCases.DELETE_CLIENT_EMPLOYEE:
                    this.clientView.deleteClient(scanner);

                case MenuCases.VIEW_CLIENT_LIST_EMPLOYEE:
                    this.clientView.viewAllClients();
                    break;

                case MenuCases.UPDATE_CLIENT_EMPLOYEE:
                    this.clientView.updateClient(scanner);
                    break;
                case "Q":
                case "q":
                    stayInEmployee = false;
                default:
                    System.out.println("Goodbye");
                    System.exit(0);
            }


        }
    }


    public void manager(Staff staff, Scanner scanner) throws Exception {

        boolean stayInManager = true;

        while (stayInManager && !isClockOut) {
            //menu item
            System.out.println();
            System.out.println("Menu items:");
            System.out.println("1. Add new Menu item");
            System.out.println("2. view all Menu Items");
            System.out.println("3. Edit Menu Item");
            System.out.println("4. delete Menu Item");
            System.out.println();
            //staff
            System.out.println("Staff:");
            System.out.println("5. Add new Staff member");
            System.out.println("6. Edit Staff personal details");
            System.out.println("7. Edit Staff user details");
            System.out.println("8. Delete Staff member");
            System.out.println("9. view all Staff members");
            System.out.println("10. view staff hour wage");
            System.out.println("11. view total staff hours report by month");
            System.out.println("12. pay salary to staff id");
            System.out.println();
            //order
            System.out.println("Orders:");
            System.out.println("13. edit order");
            System.out.println("14. add new order");
            System.out.println("15. delete order");
            System.out.println("16. view all open order");
            System.out.println("17. close order");
            System.out.println("18. view total orders report (only close)");
            System.out.println();
            //client:
            System.out.println("Clients:");
            System.out.println("19. add new client");
            System.out.println("20. delete client");
            System.out.println("21.update client ");
            System.out.println("22.view all Clients ");
            System.out.println("23.send client push ");
            System.out.println("24.Sending an email with an offer to customers who are celebrating a birthday this month ");
            System.out.println();
            System.out.println("Other:");
            System.out.println("25. clock out");
            System.out.println("Q. Exit");

            String userSelection = scanner.nextLine();

            switch (userSelection) {
                case MenuCases.SEND_BIRTHAY_CLIENT_PUSH:
                    this.clientView.sendClientBirthdayPush();
                    break;

                case MenuCases.ADD_NEW_MENU_ITEM:
                    this.menuView.addNewMenuItem(scanner);
                    break;

                case MenuCases.VIEW_ALL_MENU_ITEMS_MANAGER:
                    this.menuView.viewAllMenuItems();

                    break;

                case MenuCases.EDIT_NEW_MENU_ITEM:
                    this.menuView.editMenuItem(scanner);
                    break;

                case MenuCases.DELETE_NEW_MENU_ITEM:
                    this.menuView.deleteMenuItem(scanner);
                    break;

                case MenuCases.ADD_NEW_STAFF:
                    this.staffView.addNewStaff(scanner);
                    break;

                case MenuCases.EDIT_STAFF_PERSONAL_DETAILS:
                    this.staffView.editStaffPersonalDetails(scanner);
                    break;

                case MenuCases.EDIT_STAFF_USER_DETAILS_DETAILS:
                    this.staffView.editStaffUserDetails(scanner);
                    break;

                case MenuCases.DELETE_STAFF:
                    this.staffView.deleteStaff(scanner);
                    break;


                case MenuCases.VIEW_ALL_STAFF:
                    this.staffView.viewAllStaff();
                    break;

                case MenuCases.NEW_ORDER:
                    this.orderView.addNewOrder(scanner, staff, menuView, menuController, clientController);
                    break;

                case MenuCases.EDIT_ORDER:
                    this.orderView.editOrderAllList(scanner, menuView, menuController);
                    break;

                case MenuCases.DELETE_ORDER:
                    this.orderView.deleteOrder(scanner);
                    break;


                case MenuCases.VIEW_ALL_ORDERS:
                    this.orderView.viewAllOpenOrders();
                    break;

                case MenuCases.CLOSE_ORDER:
                    this.orderView.closeOrderAllList(scanner);
                    break;

                case MenuCases.TOTAL_ORDER_REPORT:
                    this.orderView.viewTotalOrderReport();
                    break;

                case MenuCases.CLOCK_OUT_MANAGER:
                    this.hoursReportView.clockOut(staff, shiftNum);
                    isClockOut = true;
                    break;

                case MenuCases.ADD_CLIENT:
                    this.clientView.addNewClient(scanner);
                    break;

                case MenuCases.DELETE_CLIENT:
                    this.clientView.deleteClient(scanner);
                    break;

                case MenuCases.UPDATE_CLIENT:
                    this.clientView.updateClient(scanner);
                    break;

                case MenuCases.VIEW_CLIENT_LIST:
                    this.clientView.viewAllClients();
                    break;

                case MenuCases.SEND_CLIENT_PUSH:
                    this.clientView.sendClientPush(scanner);
                    break;

                case MenuCases.VIEW_STAFF_HOUR_WAGE_REPORT:
                    this.hoursReportView.viewStaffHouerWage(scanner, staffView);
                    break;

                case MenuCases.TOTAL_STAFF_HOURS_REPORT_BY_MONTH:
                    this.hoursReportView.viewAllStaffHoursReportsByMonth(scanner);
                    break;

                case MenuCases.PAY_SALARY:
                    this.hoursReportView.paySalaryToStaffID(scanner, staffView);
                    break;


                case "q":
                case "Q":
                    stayInManager = false;
                    break;
                default:
                    System.out.println("Selection is not valid, Please select a option from the available options");
            }
        }
    }


    public void shiftManager(Staff staff, Scanner scanner) throws Exception {

        boolean stayInShiftManager = true;

        while (stayInShiftManager && !isClockOut) {

            System.out.println("");
            //order
            System.out.println("Orders:");
            System.out.println("1. edit order");
            System.out.println("2. add new order");
            System.out.println("3. delete order");
            System.out.println("4. view all open order");
            System.out.println("5. close order");
            System.out.println();

            System.out.println("Clients:");
            System.out.println("6. add new client");
            System.out.println("7. delete client");
            System.out.println("8.update client ");
            System.out.println("9.view all Clients ");
            System.out.println();
            System.out.println("Staff:");
            System.out.println("10. total staff hour today");
            System.out.println("11. view my hours report");
            System.out.println();
            System.out.println("Menu Item:");
            System.out.println("12. view all menu item");
            System.out.println();
            System.out.println("Others:");
            System.out.println("13. clock out");
            System.out.println("Q. Exit");

            String userSelection = scanner.nextLine();
            switch (userSelection) {
                case MenuCases.VIEW_MY_HOURS_REPORT_SHIFT_MANAGER:
                    this.hoursReportView.viewMyHoursReport(staff);
                    break;
                case MenuCases.VIEW_ALL_MENU_ITEMS_SHIFT_MANAGER:
                    menuView.viewAllMenuItems();
                    break;

                case MenuCases.NEW_ORDER_SHIFT_MANAGER:
                    this.orderView.addNewOrder(scanner, staff, menuView, menuController, clientController);
                    break;

                case MenuCases.EDIT_ORDER_SHIFT_MANAGER:
                    this.orderView.editOrderAllList(scanner, menuView, menuController);
                    break;

                case MenuCases.DELETE_ORDER_SHIFT_MANAGER:
                    this.orderView.deleteOrder(scanner);
                    break;


                case MenuCases.VIEW_ALL_ORDERS_SHIFT_MANAGER:
                    this.orderView.viewAllOpenOrders();
                    break;

                case MenuCases.CLOSE_ORDER_SHIFT_MANAGER:
                    this.orderView.closeOrderAllList(scanner);
                    break;


                case MenuCases.CLOCK_OUT_MANAGER_SHIFT_MANAGER:
                    this.hoursReportView.clockOut(staff, shiftNum);
                    isClockOut = true;
                    break;

                case MenuCases.ADD_CLIENT_SHIFT_MANAGER:
                    this.clientView.addNewClient(scanner);
                    break;

                case MenuCases.DELETE_CLIENT_SHIFT_MANAGER:
                    this.clientView.deleteClient(scanner);
                    break;

                case MenuCases.UPDATE_CLIENT_SHIFT_MANAGER:
                    this.clientView.updateClient(scanner);
                    break;

                case MenuCases.VIEW_CLIENT_LIST_SHIFT_MANAGER:
                    this.clientView.viewAllClients();
                    break;

                case MenuCases.TOTAL_STAFF_HOURS_REPORT_TODAY:
                    this.hoursReportView.viewAllStaffHoursReportsToday();
                    break;


                case "q":
                case "Q":
                    stayInShiftManager = false;
                    break;
                default:
                    System.out.println("Selection is not valid, Please select a option from the available options");
            }
        }
    }
}


//add  public void employee(Scanner scanner){}


