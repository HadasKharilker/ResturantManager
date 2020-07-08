package View;

import Controller.MenuController;
import Controller.OrderController;
import model.*;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class OrderView {

    private final OrderController orderController;

    public OrderView() throws Exception{
        this.orderController = OrderController.getInstance();
    }

    public void addNewOrder(Scanner scanner, Staff staff, MenuController menuController) {


        Order newOrder = getOrderFromUser(scanner, staff, menuController);
        boolean success = this.orderController.addOrder(newOrder);
        if (success) {
            System.out.println("New order added succesfully ");
        } else {
            System.out.println("Failed to add Order ");
        }
        System.out.println();
    }




    private static Order getOrderFromUser(Scanner scanner, Staff staff,MenuController menuController) {


        int staffId = staff.getPersonId();
        System.out.println("choose menu items ID from list : enter -1 to finish ");

        Set<MenuItem> menu = menuController.getAllMenuItems();

        for (MenuItem item : menu) {
            System.out.println(item);
        }
        System.out.println();

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

        Order orderFromUser = new Order(staffId, menuItems);

        return orderFromUser;
    }


    //manager order view only
    public void editOrderAllList(Scanner scanner, Staff staff,MenuController menuController) {
        System.out.println("choose order ID from list to edit:  ");
        viewAllOpenOrders();



        editOrder(scanner, staff, menuController);

    }





    public void editOrder(Scanner scanner, Staff staff,MenuController menuController){
        System.out.print("order ID: ");
        String orderID = scanner.nextLine();

        Order editOrder = getOrderFromUser(scanner, staff, menuController);

        boolean success = this.orderController.editOrder(editOrder,orderID);
        if (success) {
            System.out.println("New order added succesfully ");
        } else {
            System.out.println("Failed to add Order ");
        }
        System.out.println();



    }




    public int viewAllOpenOrders()  {

        Set<Order> orders = orderController.getAllOpenOrders();

        for (Order o : orders) {
            System.out.println(o);
        }

        return orders.size();
    }

    }














