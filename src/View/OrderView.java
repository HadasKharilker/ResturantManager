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

    public void viewTotalOrderReport() throws Exception {

        Set<Order> orders = this.orderController.getAllClosedOrders();
        double sumReport = 0;

        for (Order o : orders) {
            System.out.println(o);
            sumReport += o.getTotalPriceOrder();

        }

        System.out.println("total orders price:" + sumReport);

    }

    public void deleteOrder(Scanner scanner) throws Exception {
        System.out.println("choose order ID to delete:");

        int sizeOrder = viewAllOpenOrders();

        if (sizeOrder != 0) {
            System.out.print("order ID:");
            String orderID = scanner.nextLine();

            boolean success = this.orderController.deleteOrder(Integer.parseInt(orderID));
            if (success) {
                System.out.println("New order added succesfully ");
            } else {
                System.out.println("Failed to add Order ");
            }

        } else {
            System.out.print("no order to delete");
        }
    }

    public void closeOrderAllList(Scanner scanner, ClientController clientController) throws Exception {
        System.out.println("choose order ID to close from list:  ");
        viewAllOpenOrders();

        closeOrder(scanner, clientController);
    }

    private void closeOrder(Scanner scanner, ClientController clientController) throws Exception {
        System.out.print("order ID: ");
        String orderID = scanner.nextLine();

        Order orderToClose = this.orderController.getOrder(Integer.parseInt(orderID));
        orderToClose.setClosed(true);
        System.out.println("Total Order price: " + orderToClose.getTotalPriceOrder());

        System.out.println("press 1 to close , 0 to cancel:  ");
        String toClose = scanner.nextLine();

        if (Integer.parseInt(toClose) == 1) {
            Client client = clientController.getClient(orderToClose.getClientID());
            boolean success = orderController.closeOrder(orderToClose, client);

            if (success) {
                System.out.println("order Closed succesfully ");
            } else {
                System.out.println("Failed to close Order ");
            }
        } else
            System.out.println("Canceled");

    }


    public int viewAllOpenOrders() {

        Set<Order> orders = orderController.getAllOpenOrders();

        for (Order o : orders) {
            System.out.println(o);
        }

        return orders.size();
    }


    public void addNewOrder(Scanner scanner, Staff staff, MenuView menuView, MenuController menuController, ClientController clientController) {
        Order newOrder = getOrderFromUser(scanner, staff, menuView, menuController, clientController);

        boolean success = this.orderController.addOrder(newOrder);
        if (success) {
            System.out.println("New order added succesfully ");
        } else {
            System.out.println("Failed to add Order ");
        }
        System.out.println();
    }


    private static Order getOrderFromUser(Scanner scanner, Staff staff, MenuView menuView, MenuController menuController, ClientController clientController) {

        int staffId = staff.getPersonId();

        Set<MenuItemOrder> menuItems = getMenuItemsFromUser(scanner, menuView, menuController);
        Client client = getClientByClientIDFromUser(scanner, clientController);

        Order orderFromUser = new Order(staffId, menuItems, client.getPersonId());

        return orderFromUser;
    }

    private static Client getClientByClientIDFromUser(Scanner scanner, ClientController clientController) {

        System.out.println("Is there a customer club? press 1-yes , 0-no");
        String customerClub = scanner.nextLine();

        Client client = null;
        if (customerClub.equals("1")) {
            System.out.println("enter client ID");
            String clientID = scanner.nextLine();
            client = clientController.getClient(Integer.parseInt(clientID));
        }

        return client;

    }

    private static Set<MenuItemOrder> getMenuItemsFromUser(Scanner scanner, MenuView menuView, MenuController menuController) {
        System.out.println("choose menu items ID from list : enter -1 to finish ");
        menuView.viewAllMenuItems();

        int indexMenuItem = 1;
        Set<MenuItemOrder> menuItems = new HashSet<MenuItemOrder>();

        System.out.print("menu item " + indexMenuItem++ + ".");
        String menuItemID = scanner.nextLine();

        System.out.print("enter number of item: ");
        String numberOfItem = scanner.nextLine();

        while (!menuItemID.equals("-1")) {
            MenuItem menuItem = menuController.find(Integer.parseInt(menuItemID));
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


    //manager order view only
    public void editOrderAllList(Scanner scanner, Staff staff, MenuView menuView, MenuController menuController, ClientController clientController) {
        System.out.println("choose order ID from list to edit:  ");
        int openOrderCount = viewAllOpenOrders();

        if (openOrderCount != 0) {
            editOrder(scanner, staff, menuView, menuController, clientController);
        } else {
            System.out.println("no orders open");
        }


    }


    public void editOrder(Scanner scanner, Staff staff, MenuView menuView, MenuController menuController, ClientController clientController) {
        System.out.println("choose order ID from list to edit:  ");
        viewAllOpenOrdersByStaff(staff.getPersonId());
        String orderID = scanner.nextLine();

        Order editOrder = getOrderFromUser(scanner, staff, menuView, menuController, clientController);

        boolean success = this.orderController.editOrder(editOrder, orderID);
        if (success) {
            System.out.println("New order added succesfully ");
        } else {
            System.out.println("Failed to add Order ");
        }
        System.out.println();


    }

    public void editOrderStaffList(Scanner scanner, Staff staff, MenuView menuView, MenuController menuController, ClientController clientController) throws Exception {
        editOrder(scanner, staff, menuView, menuController, clientController);

    }

    public int viewAllOpenOrdersByStaff(int staffID) {
        Set<Order> orders = this.orderController.getAllStaffOpenOrders(staffID);

        for (Order o : orders) {
            System.out.println(o);
        }

        return orders.size();
    }

    public void deleteOrderByStaff(Scanner scanner, Staff staff) throws Exception {
        System.out.println("choose order ID to delete:");

        int sizeOrder = viewAllOpenOrdersByStaff(staff.getPersonId());

        if (sizeOrder != 0) {
            System.out.print("order ID:");
            String orderID = scanner.nextLine();

            this.orderController.deleteOrder(Integer.parseInt(orderID));

        } else {
            System.out.print("no order to delete");
        }
    }

    public void closeOrderByStaff(Scanner scanner, Staff staff, ClientController clientController) throws Exception {
        System.out.println("choose order ID to close from list:  ");
        viewAllOpenOrdersByStaff(staff.getPersonId());

        closeOrder(scanner, clientController);
    }


}














