package model;

import javax.swing.*;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Locale;
import java.util.Scanner;
import java.util.Set;


public class Main {

    public static void main(String[] args) throws Exception {
        StaffRepository staffRepository = new StaffRepositoryImpel();
        MenuRepository menuRepository = new MenuItemRepositoryImpel();
        OrderRepository orderRepository = new OrderRepositoryImpel();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Welcome!");
            System.out.println("Enter userName!");
            String userName = scanner.nextLine();
            System.out.println("Enter password!");
            String password = scanner.nextLine();

            boolean isUserCorrect = false;

            Staff staff = staffRepository.getStaffByUserName(userName);

            if (staff != null)
                isUserCorrect = staff.isPassowrdCuurect(password);

            if (isUserCorrect) {
                //staff.clockIn();
                //clock in (בתוך staff)
                while (true) {
                    if (staff.isManager())
                        managerQptions(scanner, staff, orderRepository, staffRepository, menuRepository);

                    else
                        employeeOptions(scanner, staffRepository, menuRepository);


                }
            } else {
                System.out.println("userName or password dos'nt exist!");
            }

        }

    }

    private static void managerQptions(Scanner scanner, Staff staff, OrderRepository orderRepository, StaffRepository staffRepository, MenuRepository menuRepository) throws Exception {
        System.out.println("1. Add new Menu item");
        System.out.println("2. view all Menu Items");
        System.out.println("3. Edit Menu Item");
        System.out.println("4. delete Menu Item");
        System.out.println("5. Add new Staff member");
        System.out.println("6. Edit Staff member");
        System.out.println("7. Delete Staff member");
        System.out.println("8. view all Staff members");
        System.out.println("9. view staff hour wage");
        System.out.println("10. edit order");
        System.out.println("11. add new order");
        System.out.println("12. delete order");
        System.out.println("13. view all order");
        System.out.println("14. close order");
        System.out.println("15. view total orders report (only close)");
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

            case MenuCases.EDIT_STAFF:
                //hadas
                break;

            case MenuCases.DELETE_STAFF:
                deleteStaffMember(scanner, staffRepository);
                break;

            case MenuCases.VIEW_ALL_STAFF:
                viewAllStaff(staffRepository);
                break;

            case MenuCases.VIEW_STAFF_HOUR_WAGE_REPORT:
                //hadas
                viewStaffHouerWage(scanner);
                break;

            case MenuCases.EDIT_ORDER:
                //YARDEN
                break;

            case MenuCases.NEW_ORDER:
                addNewOrder(scanner, staff, orderRepository, menuRepository);
                break;

            case MenuCases.DELETE_ORDER:
                //YARDEN
                break;

            case MenuCases.VIEW_ALL_ORDERS:
                viewAllOrders(orderRepository);
                break;

            case MenuCases.CLOSE_ORDER:

                break;

            case MenuCases.TOTAL_ORDER_REPORT:
                //YARDEN
                break;

            case "Q":
            case "q":
            default:
                System.out.println("Goodbye");
                System.exit(0);
        }
    }

    private static void viewAllOrders(OrderRepository orderRepository) throws Exception {
        Set<Order> orders = orderRepository.getAllOrders();

        for (Order o : orders) {
            System.out.println(o);

        }
    }
    private static void addNewOrder(Scanner scanner, Staff staff, OrderRepository orderRepository, MenuRepository menuRepository) throws Exception {

        int staffId = staff.getPersonId();
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
            MenuItemOrder menuItemOrder=new MenuItemOrder(menuItem,Integer.parseInt(numberOfItem));
            menuItems.add(menuItemOrder);

            System.out.print("menu item " + indexMenuItem++ + ".");
            menuItemID = scanner.nextLine();

            if(!menuItemID.equals("-1")){
            System.out.print("enter number of item: ");
            numberOfItem = scanner.nextLine();}
        }

        Order newOrder = new Order(staffId, menuItems);
        orderRepository.addOrder(newOrder);

    }

    private static void employeeOptions(Scanner scanner, StaffRepository staffRepository, MenuRepository menuRepository) throws Exception {
        System.out.println("1. view all Menu Items");
        System.out.println("2. view all Staff members");
        System.out.println("3. add new order");
        System.out.println("4. edit my order");
        System.out.println("5. delete order");
        System.out.println("6. view my orders");
        System.out.println("7. close my order");

        System.out.println("Q. Exit");

        String selectedOption = scanner.nextLine();
        switch (selectedOption) {

            case MenuCases.VIEW_ALL_MENU_ITEMS_EMPLOYEE:
                viewAllMenuItems(menuRepository);
                break;

            case MenuCases.VIEW_ALL_STAFF_EMPLOYEE:
                viewAllStaff(staffRepository);
                break;

            case MenuCases.NEW_ORDER_EMPLOYEE:
                //YARDEN
                break;

            case MenuCases.EDIT_MY_ORDER:
                //YARDEN
                break;

            case MenuCases.DELETE_MY_ORDER:
                //YARDEN
                break;

            case MenuCases.VIEW_MY_ORDERS:
                //YARDEN
                break;

            case MenuCases.CLOSE_MY_ORDER:
                //YARDEN
                break;

            case "Q":
            case "q":
            default:
                System.out.println("Goodbye");
                System.exit(0);
        }


    }

    private static void viewStaffHouerWage(Scanner scanner) throws Exception {
        System.out.print("Enter staff Id:");
        String staffID = scanner.nextLine();
    }

    private static void viewAllStaff(StaffRepository staffRepository) throws Exception {
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
        //parsing to date format

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.ENGLISH);
        LocalDate date = LocalDate.parse(d, formatter);


        if (role.equals("manager")) {
            staffRepository.addStaff(new Manager(Integer.parseInt(staffId), fName, lName, date, Integer.parseInt(houseNum), houseStreet, city, state, username, staffPassword, Role.valueOf(role)));

        }
        if (role.equals("employee")) {
            staffRepository.addStaff(new Employee(Integer.parseInt(staffId), fName, lName, date, Integer.parseInt(houseNum), houseStreet, city, state, username, staffPassword, Role.valueOf(role)));
        }

    }

    private static void deleteStaffMember(Scanner scanner, StaffRepository staffRepository) throws Exception {
        System.out.print("Enter Staff id you want to remove (number): ");
        String id = scanner.nextLine();
        staffRepository.deleteStaff(Integer.parseInt(id));

    }


    private static void editMenuItem(Scanner scanner, MenuRepository menuRepository) throws Exception {
        System.out.print("Write menu item id you want to edit (number): ");
        String id = scanner.nextLine();

        System.out.print("Entr menu item name: ");
        String name = scanner.nextLine();
        System.out.print("enter menu item price: ");
        String price = scanner.nextLine();
        System.out.print("enter menu item type(MAIN,DRINK,ALCOHOL,DESERT:");
        String type = scanner.nextLine();
        menuRepository.updateMenuItem(new MenuItem(Integer.parseInt(id), name, Double.parseDouble(price), MenuItemType.valueOf(type)));

    }

    private static void viewAllMenuItems(MenuRepository menuRepository) throws Exception {
        Set<MenuItem> menu = menuRepository.findAll();
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
