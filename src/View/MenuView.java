package View;


import Controller.MenuController;
import model.MenuItemType;

import java.util.Scanner;

public class MenuView {


        private final MenuController menuController;

        public MenuView() throws Exception{
            this.menuController = MenuController.getInstance();
        }

        public void addNewMenuItem(Scanner scanner) {
            System.out.print("Write menu item id you want to add (number): ");
            String id = scanner.nextLine();
            System.out.print("Enter menu item name: ");
            String name = scanner.nextLine();
            System.out.print("enter menu item price: ");
            String price = scanner.nextLine();
            System.out.print("enter menu item type(MAIN,DRINK,ALCOHOL,DESERT:");
            String type = scanner.nextLine();

            boolean success = this.menuController.addMenuItem(Integer.parseInt(id), name, Double.parseDouble(price), MenuItemType.valueOf(type));
            if (success) {
                System.out.println("Dish " + name + " added successfully");
            } else {
                System.out.println("Failed to add " + name);
            }
            System.out.println();
        }

        public void editMenuItem (Scanner scanner) {

            System.out.print("Write menu item id you want to edit (number): ");
            String id = scanner.nextLine();
            System.out.print("Enter menu item name: ");
            String name = scanner.nextLine();
            System.out.print("enter menu item price: ");
            String price = scanner.nextLine();
            System.out.print("enter menu item type(MAIN,DRINK,ALCOHOL,DESERT:");
            String type = scanner.nextLine();

            boolean success = this.menuController.editMenuItem(Integer.parseInt(id), name, Double.parseDouble(price), MenuItemType.valueOf(type));
            if (success) {
                System.out.println("Dish " + name + " changed successfully");
            } else {
                System.out.println("Failed to add " + name);
            }
            System.out.println();
        }

    public void deleteMenuItem(Scanner scanner) {
        System.out.print("Enter menu item id you want to remove (number): ");
        String id = scanner.nextLine();

        boolean success = this.menuController.deleteMenuItem(Integer.parseInt(id));
        if (success) {
            System.out.println("Dish  removed successfully");
        } else {
            System.out.println("Failed to remove ");
        }
        System.out.println();
    }







    }


