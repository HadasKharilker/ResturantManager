package model;

import javax.swing.*;
import java.util.Scanner;
import java.util.Set;

public class Main {

	public static void main(String[] args) throws Exception {
		MenuRepository menuRepository = new MenuItemRepositoryImpel();
		Scanner scanner = new Scanner(System.in);
		while (true) {
			System.out.println("Welcome!");
			System.out.println("1. Add new Menu item");
			System.out.println("2. Print all Menu Items");
			System.out.println("3. Edit Menu Item");
			System.out.println("Q. Exit");

			String selectedOption = scanner.nextLine();
			switch (selectedOption) {
				case "1":
					System.out.print("Write menu item id (number): ");
					String id = scanner.nextLine();
					System.out.print("Entr menu item name: ");
					String name = scanner.nextLine();
					System.out.print("enter menu item price: ");
					String price= scanner.nextLine();
					System.out.print("enter menu item type(MAIN,DRINK,ALCOHOL,DESERT:");
					String type= scanner.nextLine();

					menuRepository.addMenuItem(new MenuItem(Integer.parseInt(id), name, Double.parseDouble(price),MenuItemType.valueOf(type)));
					break;
				case "2":
					Set<MenuItem> menu = menuRepository.findAll();
					for (MenuItem items : menu) {
						System.out.println(items);
					}
					break;
				case "3":
					System.out.print("Write menu item id you want to edit (number): ");
					 id = scanner.nextLine();

					System.out.print("Entr menu item name: ");
					name = scanner.nextLine();
					System.out.print("enter menu item price: ");
					 price= scanner.nextLine();
					System.out.print("enter menu item type(MAIN,DRINK,ALCOHOL,DESERT:");
					type= scanner.nextLine();
					menuRepository.updateMenuItem(new MenuItem(Integer.parseInt(id), name, Double.parseDouble(price),MenuItemType.valueOf(type)));
					break;
				case "Q":
				case "q":
				default:
					System.out.println("Goodbye");
					System.exit(0);
			}
		}
	}
}
