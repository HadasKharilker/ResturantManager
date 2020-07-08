package View;


import java.util.Scanner;
import java.util.Set;


import Controller.LoginController;
import Controller.OrderController;
import Controller.StaffController;
import model.MenuCases;
import Controller.MenuController;
import model.MenuItem;
import model.Order;
import model.Staff;

public class RestaurantView {


    private final LoginController loginController;
    private final MenuController menuController;
    private final MenuView menuView;


    //Staff View
    private final StaffController staffController;
    private final StaffView staffView;


    //Order
    private final OrderController orderController;
    private final OrderView orderView;



    //להשלים
    //private final EmployeeView employeeView;

    //להשלים
    //private final EmployeeController employeeController;

    public RestaurantView() throws Exception {
        this.menuView = new MenuView();
        this.staffView=new StaffView();
        this.orderView=new OrderView();

        //this.employeeView = new EmployeeView(); לשנות להזמנות, עובדים ועוד...
        this.menuController = MenuController.getInstance();
        this.staffController = StaffController.getInstance();
        this.orderController= OrderController.getInstance();

        // this.employeeController = EmployeeController.getInstance();לשנות להזמנות, עובדים ועוד...
        this.loginController = LoginController.getInstance();

    }

    public void start() throws Exception {
        System.out.println("Welcome to the Resturant Manager - The Right Way For Your Business");
        boolean stayInResturant = true;
        try (Scanner scanner = new Scanner(System.in)) {
            while (stayInResturant) {
                System.out.println("1. login");
                System.out.println("Q. exit");

                String command = scanner.nextLine();
                switch (command) {
                    case "1":
                        this.login(scanner);
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

    public void login(Scanner scanner) throws Exception {

        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        Integer typeOfLogin = loginController.login(username, password);

        //manager View
        if (typeOfLogin == 1) {
            System.out.println("You are now logged in as manager");
            this.manager(scanner);


            //employeeView
                /*
            } else if (typeOfLogin == 0) {
                System.out.println("You are now logged in as employee");
                this.employee(scanner);
*/

        } else
            System.out.println("userName or password dos'nt exist!");
    }


    public void manager(Scanner scanner) {
        boolean stayInManager = true;

        while (stayInManager) {

            //MenuView
            System.out.println("");
            System.out.println("1. Add new Menu item");
            System.out.println("2. view all Menu Items");
            System.out.println("3. Edit Menu Item");
            System.out.println("4. delete Menu Item");

            //StaffView
            System.out.println("5. Add new Staff member");
            System.out.println("6. Edit Staff personal details");
            System.out.println("7. Edit Staff user details");
            System.out.println("8. Delete Staff member");
            System.out.println("9. view all Staff members");
            System.out.println("10. view staff hour wage");

            //OrderView
            System.out.println("11. edit order");
            System.out.println("12. add new order");
            System.out.println("13. delete order");
            System.out.println("14. view all orders");
            System.out.println("15. close order");
            System.out.println("16. view total orders report (only close)");
            System.out.println("Q. Exit");

            String userSelection = scanner.nextLine();
            switch (userSelection) {

                case MenuCases.ADD_NEW_MENU_ITEM:
                    this.menuView.addNewMenuItem(scanner);
                    break;

                case MenuCases.VIEW_ALL_MENU_ITEMS_MANAGER:
                    Set<MenuItem> menu = this.menuController.getAllMenuItems();
                    System.out.println("Available Dishes in Menu:");
                    for (MenuItem item : menu) {
                        System.out.println(item);
                    }
                    System.out.println();
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
                    Set<Staff> staff = this.staffController.getAllStaff();
                    System.out.println("List of Staff members:");
                    for (Staff s : staff) {
                        System.out.println(s);
                    }
                    System.out.println();
                    break;






                case MenuCases.VIEW_ALL_ORDERS:
                    Set<Order> orders = this.orderController.getAllOpenOrders();
                    System.out.println("List of orders members:");
                    for (Order s : orders) {
                        System.out.println(s);
                    }
                    System.out.println();
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



    //add  public void employee(Scanner scanner){}





}