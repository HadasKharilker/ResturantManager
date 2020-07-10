package View;


import Controller.MenuController;
import model.MenuItem;
import model.MenuItemType;
import model.Staff;

import java.util.Scanner;
import java.util.Set;

public class MenuView {


    private final MenuController menuController;

    public MenuView() throws Exception {
        this.menuController = MenuController.getInstance();
    }

    public void addNewMenuItem(Scanner scanner) {
        System.out.print("Write menu item id you want to add (number): ");
        String id = scanner.nextLine();
        System.out.print("Enter menu item name: ");
        String name = scanner.nextLine();
        System.out.print("enter menu item price: ");
        String price = scanner.nextLine();
        System.out.print("enter menu item type(MAIN,DRINK,ALCOHOL,DESERT) :");
        String type = scanner.nextLine();

        boolean success = this.menuController.addMenuItem(id, name, price, type);
        if (success) {
            System.out.println("Dish " + name + " added successfully");
        } else {
            System.out.println("Failed to add " + name);
        }
        System.out.println();
    }


    public void editMenuItem(Scanner scanner) {

        System.out.print("Write menu item id you want to edit (number): ");
        viewAllMenuItems();

        String menuID = scanner.nextLine();
        System.out.print("Enter menu item name: ");
        String name = scanner.nextLine();
        System.out.print("enter menu item price: ");
        String price = scanner.nextLine();
        System.out.print("enter menu item type(MAIN,DRINK,ALCOHOL,DESERT:");
        String type = scanner.nextLine();

        boolean success = this.menuController.editMenuItem(menuID, name, price, type);
        if (success) {
            System.out.println("Dish " + name + " changed successfully");
        } else {
            System.out.println("Failed to edit " + name);
        }
        System.out.println();
    }

    public void deleteMenuItem(Scanner scanner) {
        System.out.print("Enter menu item id you want to remove (number): ");
        viewAllMenuItems();
        String menuID = scanner.nextLine();

        boolean success = this.menuController.deleteMenuItem(menuID);
        if (success) {
            System.out.println("Menu " + menuID + " delete successfully");
        } else {
            System.out.println("Failed to delete " + menuID);
        }
        System.out.println();
    }


    public void viewAllMenuItems() {
        boolean success = this.menuController.printAllMenu();
        if (!success) {
            System.out.println("Failed to viewAllMenuItems ");
        }

        System.out.println();


    }

}


