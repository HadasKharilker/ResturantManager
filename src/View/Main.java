package View;

import model.*;

import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.util.*;


public class Main {
    public static boolean isClockOut = false;

    public static void main(String[] args) throws Exception {
        RestaurantView resturantView = new RestaurantView();
        resturantView.start(resturantView);
    }
/*

        StaffRepository staffRepository = StaffRepositoryImpel.getInstance();
        MenuRepository menuRepository = MenuItemRepositoryImpel.getInstance();
        OrderRepository orderRepository = OrderRepositoryImpel.getInstance();
        HoursReportRepository hoursReportRepository = HoursReportRepositoryImpel.getInstance();
        ClientRepository clientRepository = ClientRepositoryImpel.getInstance();
        Scanner scanner = new Scanner(System.in);
        int shiftNum;

//addNewStaff(scanner,staffRepository);
        while (true) {


            System.out.println("Welcome!");
            System.out.println("Enter userName!");
            String userName = scanner.nextLine();
            System.out.println("Enter password!");
            String password = scanner.nextLine();

            boolean isUserCorrect = false;

            Staff staff = staffRepository.getStaffByUserName(userName);

            if (staff != null)
                isUserCorrect = staff.isPasswordCorrect(password);

            if (isUserCorrect) {
                isClockOut = false;

                shiftNum = hoursReportRepository.clockIn(staff);
                System.out.println("Clock in");

                while (!isClockOut) {
                    if (staff.isManager())
                        managerOptions(scanner, shiftNum, clientRepository, hoursReportRepository, staff, orderRepository, staffRepository, menuRepository);

                    else
                        employeeOptions(scanner, shiftNum, clientRepository, hoursReportRepository, staff, orderRepository, menuRepository);


                }
            } else {
                System.out.println("userName or password dos'nt exist!");
            }

        }

    }

    private static void managerOptions(Scanner scanner, int shiftNum, ClientRepository clientRepository, HoursReportRepository hoursReportRepository, Staff staff, OrderRepository orderRepository, StaffRepository staffRepository, MenuRepository menuRepository) throws Exception {
        System.out.println("");
        //menu item
        System.out.println("1. Add new Menu item");
        System.out.println("2. view all Menu Items");
        System.out.println("3. Edit Menu Item");
        System.out.println("4. delete Menu Item");

        //staff

        System.out.println("6. Edit Staff personal details");
        System.out.println("7. Edit Staff user details");
        System.out.println("8. Delete Staff member");
        System.out.println("9. view all Staff members");
        System.out.println("10. view staff hour wage");
        System.out.println("18. view total staff hours report by month");

        //order
        System.out.println("11. edit order");
        System.out.println("12. add new order");
        System.out.println("13. delete order");
        System.out.println("14. view all order");
        System.out.println("15. close order");
        System.out.println("16. view total orders report (only close)");

        System.out.println("17. clock out");

        System.out.println("19. pay salary to staff id");
        //client:
        System.out.println("20. add new client");
        System.out.println("21. delete client");
        System.out.println("22.update client ");
        System.out.println("23.view all Clients ");
        System.out.println("24.send client push ");
        System.out.println("Q. Exit");

        String selectedOption = scanner.nextLine();
        switch (selectedOption) {


            case "Q":
            case "q":
            default:
                System.out.println("Goodbye");
                System.exit(0);
        }
    }


*/


}
