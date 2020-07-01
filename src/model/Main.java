package model;

import javax.swing.*;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.util.Locale;
import java.util.Scanner;
import java.util.Set;



public class Main {

	public static void main(String[] args) throws Exception {

		StaffRepository staffRepository= new StaffRepositoryImpel();



		Scanner scanner = new Scanner(System.in);
		while (true) {
			System.out.println("Welcome!");
			System.out.println("Enter userName!");
			String userName = scanner.nextLine();
			System.out.println("Enter password!");
			String password = scanner.nextLine();
			boolean isUserCorrect=false;

			Staff staff=staffRepository.getStaffByUserName(userName);

			if(staff!=null)
				 isUserCorrect = staff.isPassowrdCuurect(password);

			if(isUserCorrect)
			{

				//staff.clockIn();
				//clock in (in staff)
				MenuRepository menuRepository = new MenuItemRepositoryImpel();

				if (isManager(staff)) {
					callManagerQuestions();

					String selectedOption = scanner.nextLine();
					switch (selectedOption) {
						case MenuCases.ADD_NEW_MENU_ITEM:
							System.out.print("Write menu item id (number): ");
							String id = scanner.nextLine();
							System.out.print("Entr menu item name: ");
							String name = scanner.nextLine();
							System.out.print("enter menu item price: ");
							String price = scanner.nextLine();
							System.out.print("enter menu item type(MAIN,DRINK,ALCOHOL,DESERT:");
							String type = scanner.nextLine();

							menuRepository.addMenuItem(new MenuItem(Integer.parseInt(id), name, Double.parseDouble(price), MenuItemType.valueOf(type)));
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
							price = scanner.nextLine();
							System.out.print("enter menu item type(MAIN,DRINK,ALCOHOL,DESERT:");
							type = scanner.nextLine();
							menuRepository.updateMenuItem(new MenuItem(Integer.parseInt(id), name, Double.parseDouble(price), MenuItemType.valueOf(type)));
							break;
						case "4":
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


							break;
						case "7":
							Set<Staff> staffSet = staffRepository.findAll();
							for (Staff s : staffSet) {


								System.out.println(s);

							}
							break;


						case "8":
							System.out.print("Enter staff Id:");
							String staffID = scanner.nextLine();


							break;
						case "Q":
						case "q":
						default:
							System.out.println("Goodbye");
							System.exit(0);
					}

				}

				else {
					callEmployeeQuestions();

					String selectedOption = scanner.nextLine();
					switch (selectedOption) {
						case MenuCases.ADD_NEW_MENU_ITEM:
							System.out.print("Write menu item id (number): ");
							String id = scanner.nextLine();
							System.out.print("Entr menu item name: ");
							String name = scanner.nextLine();
							System.out.print("enter menu item price: ");
							String price = scanner.nextLine();
							System.out.print("enter menu item type(MAIN,DRINK,ALCOHOL,DESERT:");
							String type = scanner.nextLine();

							menuRepository.addMenuItem(new MenuItem(Integer.parseInt(id), name, Double.parseDouble(price), MenuItemType.valueOf(type)));
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
							price = scanner.nextLine();
							System.out.print("enter menu item type(MAIN,DRINK,ALCOHOL,DESERT:");
							type = scanner.nextLine();
							menuRepository.updateMenuItem(new MenuItem(Integer.parseInt(id), name, Double.parseDouble(price), MenuItemType.valueOf(type)));
							break;
						case "4":
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


							break;
						case "7":
							Set<Staff> staffSet = staffRepository.findAll();
							for (Staff s : staffSet) {


								System.out.println(s);

							}
							break;


						case "8":
							System.out.print("Enter staff Id:");
							String staffID = scanner.nextLine();


							break;
						case "Q":
						case "q":
						default:
							System.out.println("Goodbye");
							System.exit(0);
					}
				}
			}


			else
			{
				System.out.println("userName or password dos'nt exist!");
			}

			}

		}


		private static void callManagerQuestions(){
			System.out.println("1. Add new Menu item");
			System.out.println("2. view all Menu Items");
			System.out.println("3. Edit Menu Item");
			System.out.println("3. delete Menu Item");
			System.out.println("4. Add new Staff member");
			System.out.println("5. Edit Staff member");
			System.out.println("6. Delete Staff member");
			System.out.println("7. view all Staff members");
			System.out.println("8. edit order");
			System.out.println("9. add new order");
			System.out.println("10. delete order");
			System.out.println("11. view all order");
			System.out.println("12. close order");
			System.out.println("13. view total orders report (only close)");
			System.out.println("14. view staff hour wage");

			System.out.println("Q. Exit");
		}

	private static void callEmployeeQuestions(){
		//manager:


		//emplyee:

		System.out.println("1. view all Menu Items");
		System.out.println("2. view all Staff members");
		System.out.println("3. add new order");
		System.out.println("4. edit my order");
		System.out.println("5. delete order");
		System.out.println("6. view my orders");
		System.out.println("7. close my order");

		System.out.println("Q. Exit");


	}

	private static boolean isManager(Staff staff)
	{
		return staff.getUserDetails().getRole() == Role.manager;
	}




}
