package model;

import javax.swing.*;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.util.Locale;
import java.util.Scanner;
import java.util.Set;



public class Main {

	public static void main(String[] args) throws Exception {
		MenuRepository menuRepository = new MenuItemRepositoryImpel();
		StaffRepository staffRepository= new StaffRepositoryImpel();



		Scanner scanner = new Scanner(System.in);
		while (true) {
			System.out.println("Welcome!");

			System.out.println("1. Add new Menu item");
			System.out.println("2. Print all Menu Items");
			System.out.println("3. Edit Menu Item");
			System.out.println("4. Add new Staff member");
			//System.out.println("5. Edit Staff member");
			//System.out.println("6. Delete Staff member");
			System.out.println("7. Print all Staff members");
			System.out.println("8. edit Staff");
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
				case "4":
					System.out.print("Enter staff id : ");
					String staffId = scanner.nextLine();
					System.out.print("Entr first name : ");
					String fName = scanner.nextLine();
					System.out.print("Enter last name : ");
					String lName= scanner.nextLine();
					System.out.print("Enter birth date in this format (dd/mm/yyyy) :");
					String d= scanner.nextLine();

					System.out.print("Enter private house number:");
					String houseNum= scanner.nextLine();
					System.out.print("Enter house street:");
					String houseStreet= scanner.nextLine();
					System.out.print("Enter city:");
					String city= scanner.nextLine();
					System.out.print("Enter state:");
					String state= scanner.nextLine();
					System.out.print("Enter role (manager/employee:");
					String role= scanner.nextLine();
					System.out.print("Enter User Name:");
					String username= scanner.nextLine();
					System.out.print("Enter password:");
					String password= scanner.nextLine();
					//parsing to date format

					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.ENGLISH);
					LocalDate date = LocalDate.parse(d, formatter);


					if (role.equals("manager"))
					{
					 staffRepository.addStaff(new Manager(Integer.parseInt(staffId), fName,lName,date , Integer.parseInt(houseNum),houseStreet,city,state,username,password,Role.valueOf(role)));

					}
					 if(role.equals("employee")) {
						 staffRepository.addStaff(new Employee(Integer.parseInt(staffId), fName, lName, date, Integer.parseInt(houseNum), houseStreet, city, state, username, password, Role.valueOf(role)));
					}




					break;
				case "7":
					Set<Staff> staff = staffRepository.findAll();
					for (Staff s : staff) {


						System.out.println(s);

					}
					break;


				case "8":



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
