package View;

import Controller.ClientController;
import Controller.MenuController;
import Controller.OrderController;
import model.*;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class OrderView {

    private final OrderController orderController;

    public OrderView() throws Exception {
        this.orderController = OrderController.getInstance();
    }


    public void editOrderAllList(Scanner scanner, MenuView menuView, MenuController menuController) {

        System.out.println("choose order ID from list to edit:  ");
        viewAllOpenOrders();
        String orderID = scanner.nextLine();

        Set<MenuItemOrder> menuItemOrders = getMenuItemsFromUser(scanner, menuView, menuController);

        System.out.println("Is there a customer club? press 1-yes , 0-no");
        String customerClub = scanner.nextLine();

        String clientID = "";
        if (customerClub.equals("1")) {
            System.out.println("enter client ID");
            clientID = scanner.nextLine();
        }

        boolean success = this.orderController.editOrder(orderID, menuItemOrders, clientID);
        if (success) {
            System.out.println("New order added succesfully ");
        } else {
            System.out.println("Failed to add Order ");
        }
        System.out.println();


    }


    public void editOrderStaffList(Scanner scanner, Staff staff, MenuView menuView, MenuController menuController) {
        System.out.println("choose order ID from list to edit:  ");
        viewAllOpenOrdersByStaff(staff);
        String orderID = scanner.nextLine();

        Set<MenuItemOrder> menuItemOrders = getMenuItemsFromUser(scanner, menuView, menuController);

        System.out.println("Is there a customer club? press 1-yes , 0-no");
        String customerClub = scanner.nextLine();

        String clientID = "";
        if (customerClub.equals("1")) {
            System.out.println("enter client ID");
            clientID = scanner.nextLine();
        }

        boolean success = this.orderController.editOrder(orderID, menuItemOrders, clientID);
        if (success) {
            System.out.println("New order added succesfully ");
        } else {
            System.out.println("Failed to add Order ");
        }
        System.out.println();

    }


    public void closeOrderByStaff(Scanner scanner, Staff staff) {
        System.out.println("choose order ID to close from list:  ");
        viewAllOpenOrdersByStaff(staff);
        String orderID = scanner.nextLine();

        boolean success = this.orderController.closeOrder(orderID);
        if (success) {
            System.out.println("order closed ");
        } else {
            System.out.println("Failed to close order ");
        }
        System.out.println();
    }


    public void closeOrderAllList(Scanner scanner) {
        System.out.println("choose order ID to close from list:  ");
        viewAllOpenOrders();
        String orderID = scanner.nextLine();

        boolean success = this.orderController.closeOrder(orderID);
        if (success) {
            System.out.println("order closed ");
        } else {
            System.out.println("Failed to close order ");
        }
        System.out.println();
    }

    public void addNewOrder(Scanner scanner, Staff staff, MenuView menuView, MenuController menuController, ClientController clientController) {
        Set<MenuItemOrder> menuItemOrders = getMenuItemsFromUser(scanner, menuView, menuController);

        System.out.println("Is there a customer club? press 1-yes , 0-no");
        String customerClub = scanner.nextLine();

        String clientID = "";
        if (customerClub.equals("1")) {
            System.out.println("enter client ID");
            clientID = scanner.nextLine();
        }

        boolean success = this.orderController.addOrder(staff, menuItemOrders, clientID);
        if (success) {
            System.out.println("New order added succesfully ");
        } else {
            System.out.println("Failed to add Order ");
        }
        System.out.println();
    }

    private static Set<MenuItemOrder> getMenuItemsFromUser(Scanner scanner, MenuView menuView, MenuController menuController) {
        try {
            System.out.println("choose menu items ID from list : enter -1 to finish ");
            menuView.viewAllMenuItems();

            int indexMenuItem = 1;
            Set<MenuItemOrder> menuItems = new HashSet<MenuItemOrder>();

            System.out.print("menu item " + indexMenuItem++ + ".");
            String menuItemID = scanner.nextLine();

            System.out.print("enter number of item: ");
            String numberOfItem = scanner.nextLine();

            while (!menuItemID.equals("-1")) {
                MenuItem menuItem = menuController.findItem(Integer.parseInt(menuItemID));
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
        } catch (Exception ex) {
            System.out.println("Failed to getMenuItemsFromUser ");

            return null;
        }
    }

    public void viewAllOpenOrders() {
        boolean success = this.orderController.viewAllOpenOrders();
        if (!success)
            System.out.println("Failed to viewAllOpenOrders ");

    }

    public void viewAllOpenOrdersByStaff(Staff staff) {
        boolean success = this.orderController.viewAllOpenOrdersByStaff(staff);
        if (!success)
            System.out.println("Failed to viewAllOpenOrdersByStaff ");

    }

    public void deleteOrderByStaff(Scanner scanner, Staff staff) {

        System.out.println("choose order ID to delete:");
        viewAllOpenOrdersByStaff(staff);
        String orderID = scanner.nextLine();

        boolean success = this.orderController.deleteOrder(orderID);
        if (success) {
            System.out.println("order deleted succesfully ");
        } else {
            System.out.println("Failed to delete Order ");
        }

    }

    public void viewTotalOrderReport() {
        boolean success = this.orderController.viewTotalOrderReport();

        if (!success)
            System.out.println("Failed to viewTotalOrderReport ");

    }

    public void deleteOrder(Scanner scanner) {
        System.out.println("choose order ID to delete:");
        viewAllOpenOrders();
        String orderID = scanner.nextLine();

        boolean success = this.orderController.deleteOrder(orderID);
        if (success) {
            System.out.println("New order added succesfully ");
        } else {
            System.out.println("Failed to add Order ");
        }
    }
}














