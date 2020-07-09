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
        try {
            System.out.print("Write menu item id you want to add (number): ");
            String id = scanner.nextLine();
            System.out.print("Enter menu item name: ");
            String name = scanner.nextLine();
            System.out.print("enter menu item price: ");
            String price = scanner.nextLine();
            System.out.print("enter menu item type(MAIN,DRINK,ALCOHOL,DESERT) :");
            String type = scanner.nextLine();
            MenuItem item = new MenuItem.MenuItemBuilder(Integer.parseInt(id)).itemName(name).price(Double.parseDouble(price)).itemType(MenuItemType.valueOf(type)).build();

            boolean success = this.menuController.addMenuItem(item);
            if (success) {
                System.out.println("Dish " + name + " added successfully");
            } else {
                System.out.println("Failed to add " + name);
            }
            System.out.println();

        } catch (Exception ex) {
            System.out.println("Failed to addNewMenuItem");
        }
    }


    public void viewAllMenuItems() {
        try {
            Set<MenuItem> menu = this.menuController.getAllMenuItems();
            System.out.println("Available Dishes in Menu:");
            for (MenuItem item : menu) {
                System.out.println(item);
            }

        } catch (Exception ex) {
            System.out.println("Failed to viewAllMenuItems");
        }
    }

    public void editMenuItem(Scanner scanner) {
        try {
            System.out.print("Write menu item id you want to edit (number): ");
            viewAllMenuItems();
            String id = scanner.nextLine();
            System.out.print("Enter menu item name: ");
            String name = scanner.nextLine();
            System.out.print("enter menu item price: ");
            String price = scanner.nextLine();
            System.out.print("enter menu item type(MAIN,DRINK,ALCOHOL,DESERT:");
            String type = scanner.nextLine();
            MenuItem item = new MenuItem.MenuItemBuilder(Integer.parseInt(id)).itemName(name).price(Double.parseDouble(price)).itemType(MenuItemType.valueOf(type)).build();

            boolean success = this.menuController.editMenuItem(item);
            if (success) {
                System.out.println("Dish " + name + " changed successfully");
            } else {
                System.out.println("Failed to add " + name);
            }
            System.out.println();

        } catch (Exception ex) {
            System.out.println("Failed to editMenuItem");
        }
    }

    public void deleteMenuItem(Scanner scanner) {
        try {
            System.out.print("Enter menu item id you want to remove (number): ");
            viewAllMenuItems();
            String id = scanner.nextLine();

            boolean success = this.menuController.deleteMenuItem(Integer.parseInt(id));
            if (success) {
                System.out.println("Dish  removed successfully");
            } else {
                System.out.println("Failed to remove ");
            }
            System.out.println();

        } catch (Exception ex) {
            System.out.println("Failed to deleteMenuItem");
        }
    }


}


