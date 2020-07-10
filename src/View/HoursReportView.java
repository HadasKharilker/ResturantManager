package View;

import Controller.HoursReportController;
import Controller.StaffController;
import model.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Scanner;
import java.util.Set;

public class HoursReportView {
    private final HoursReportController hoursReportController;

    public HoursReportView() throws Exception {
        this.hoursReportController = HoursReportController.getInstance();
    }

    public void viewAllStaffHoursReportsToday() {
        boolean success = this.hoursReportController.viewAllStaffHoursReportsToday();

        if (!success)
            System.out.println("Failed to view All Staff Hours Reports Today");
    }
    public void viewAllStaffHoursReportsByMonth(Scanner scanner) {

        System.out.println("Enter month to watch:");
        String month = scanner.nextLine();

        boolean success = this.hoursReportController.viewAllStaffHoursReportsByMonth(month);
        if (!success)
            System.out.println("Failed to view All Staff Hours Reports By Month");
    }
    public void viewStaffHouerWage(Scanner scanner, StaffView staffView) {

        System.out.println("Enter staff Id from staff list:");
        staffView.viewAllStaff();
        String staffID = scanner.nextLine();

        boolean success = this.hoursReportController.viewStaffHouerWage(staffID);
        if (!success)
            System.out.println("Failed to viewStaffHouerWage");
    }
    public void paySalaryToStaffID(Scanner scanner, StaffView staffView) {
        System.out.print("choose staff Id from list : ");
        staffView.viewAllStaff();
        String staffID = scanner.nextLine();

        System.out.println("Enter month to pay:");
        String month = scanner.nextLine();

        boolean success = this.hoursReportController.paySalaryToStaffID(staffID, month);
        if (success) {
            System.out.println("pay successfully");
        } else {
            System.out.println("Failed to pay salary");
        }
    }
    public void viewMyHoursReport(Staff staff) {
        boolean success = this.hoursReportController.viewMyHoursReport(staff);

        if (!success) {
            System.out.println("Failed to viewMyHoursReport");

            System.out.println();
        }
    }
    public void clockOut(Staff staff, int shiftNum) {

        boolean success = this.hoursReportController.clockOut(staff, shiftNum);
        if (success) {
            System.out.println("Clocked out");
        } else {
            System.out.println("Failed to Clocked out");
        }
    }
    public int clockIn(Staff staff) {

        int shiftNum = this.hoursReportController.clockIn(staff);

        if (shiftNum != 0) {
            System.out.println("Clocked in");
        } else {
            System.out.println("Failed to Clocked in");
        }

        return shiftNum;
    }


}

