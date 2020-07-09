package View;


import java.util.Scanner;
import java.util.Set;


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
    //להשלים
    //private final EmployeeView employeeView;

    //להשלים
    //private final EmployeeController employeeController;

    public RestaurantView() throws Exception {
        this.menuView = new MenuView();
        this.staffView = new StaffView();
        this.orderView = new OrderView();
        this.clientView = new ClientView();
        this.hoursReportView = new HoursReportView();

        //this.employeeView = new EmployeeView(); לשנות להזמנות, עובדים ועוד...
        this.menuController = MenuController.getInstance();
        this.staffController = StaffController.getInstance();
        this.orderController = OrderController.getInstance();
        this.clientController = ClientController.getInstance();
        this.hoursReportController = HoursReportController.getInstance();
        // this.employeeController = EmployeeController.getInstance();לשנות להזמנות, עובדים ועוד...
        this.loginController = LoginController.getInstance();

    }

    public void start(RestaurantView resturantView) throws Exception {
        System.out.println("Welcome to the Resturant Manager - The Right Way For Your Business");
        boolean stayInResturant = true;
        try (Scanner scanner = new Scanner(System.in)) {
            while (stayInResturant) {
                System.out.println("1. login");
                System.out.println("Q. exit");

                String command = scanner.nextLine();
                switch (command) {
                    case "1":
                        this.login(scanner,resturantView);
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

        boolean login = loginController.login(username, password, hoursReportView, resturantView);
        if(login) {
            Staff staff = loginController.getStaffByUserName(username);
            if (staff.isManager()) {
                System.out.println("You are now logged in as manager");
                resturantView.manager(staff);
            } else {
                System.out.println("You are now logged in as employee");
                resturantView.employee(staff);
            }
        }
        else
            System.out.println("User name or password is wrong");

    }




    public void employee( Staff staff) throws Exception {
        try (Scanner scanner = new Scanner(System.in)) {
        boolean stayInEmployee = true;

        while (stayInEmployee && !isClockOut) {
            System.out.println("1. view all Menu Items");
            System.out.println("2. add new order");
            System.out.println("3. edit my order");
            System.out.println("4. delete order");
            System.out.println("5. view my open orders");
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
                    menuView.viewAllMenuItems();
                    break;


                case MenuCases.NEW_ORDER_EMPLOYEE:
                    this.orderView.addNewOrder(scanner, staff, menuView, menuController, clientController);
                    break;

                case MenuCases.EDIT_MY_ORDER:
                    this.orderView.editOrderStaffList(scanner, staff, menuView, menuController, clientController);
                    break;

                case MenuCases.DELETE_MY_ORDER:
                    this.orderView.deleteOrderByStaff(scanner, staff);
                    break;

                case MenuCases.VIEW_MY_ORDERS:
                    this.orderView.viewAllOpenOrdersByStaff(staff.getPersonId());
                    break;

                case MenuCases.CLOSE_MY_ORDER:
                    this.orderView.closeOrderByStaff(scanner, staff, clientController);
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
    }

    public void manager( Staff staff) throws Exception {
        try (Scanner scanner = new Scanner(System.in)) {
            boolean stayInManager = true;

            while (stayInManager && !isClockOut) {

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
                System.out.println("14. view all open order");
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

                String userSelection = scanner.nextLine();
                switch (userSelection) {

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
                        this.orderView.editOrderAllList(scanner, staff, menuView, menuController, clientController);
                        break;

                    case MenuCases.DELETE_ORDER:
                        this.orderView.deleteOrder(scanner);
                        break;


                    case MenuCases.VIEW_ALL_ORDERS:
                        this.orderView.viewAllOpenOrders();
                        break;

                    case MenuCases.CLOSE_ORDER:
                        this.orderView.closeOrderAllList(scanner, clientController);
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
                        this.hoursReportView.viewStaffHouerWage(scanner);
                        break;

                    case MenuCases.TOTAL_STAFF_HOURS_REPORT_BY_MONTH:
                        this.hoursReportView.viewAllStaffHoursReports(scanner);
                        break;

                    case MenuCases.PAY_SALARY:
                        this.hoursReportView.paySalaryToStaffID(scanner, staffView, staffController);
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
    }


    //add  public void employee(Scanner scanner){}


}