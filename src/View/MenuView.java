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
    }


