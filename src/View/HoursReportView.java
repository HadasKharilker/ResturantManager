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

    public void clockOut(Staff staff, int shiftNum) throws Exception {

        boolean success = this.hoursReportController.clockOut(staff, shiftNum);
        if (success) {
            System.out.println("Clocked out");
        } else {
            System.out.println("Failed to Clocked out");
        }
    }

    public int clockIn(Staff staff) throws Exception {

        int shiftNum = this.hoursReportController.clockIn(staff);
        if (shiftNum != 0) {
            System.out.println("Clocked in");
        } else {
            System.out.println("Failed to Clocked in");
        }

        return shiftNum;
    }

    public void viewStaffHouerWage(Scanner scanner, StaffView staffView) throws Exception {
        try {
            System.out.println("Enter staff Id from staff list:");
            staffView.viewAllStaff();
            String staffID = scanner.nextLine();

            Set<StaffHour> staffHours = this.hoursReportController.getStaffHourBy(Integer.parseInt(staffID));
            printStaffHourList(staffHours);

        } catch (Exception ex) {
            System.out.println("Failed to viewStaffHouerWage");

        }
    }

    public void printStaffHourList(Set<StaffHour> staffHours) throws Exception {
        try {
            int totalHours = 0;
            int totalMin = 0;
            double totalSalary = 0;

            for (StaffHour staffHour : staffHours) {
                System.out.println(staffHour);
                totalHours += getHoursDifference(staffHour.getClockInDate(), staffHour.getClockOutDate());
                totalMin += getMinDifference(staffHour.getClockInDate(), staffHour.getClockOutDate());
            }
            System.out.println("total hours: " + totalHours + " Minutes: " + totalMin);

        } catch (Exception ex) {
            System.out.println("Failed to print Staff Hour List");

        }
    }

    private long getMinDifference(Date date1, Date date2) {

        long diff = date2.getTime() - date1.getTime();

        long diffMinutes = diff / (60 * 1000) % 60;

        return diffMinutes;

    }

    private long getHoursDifference(Date date1, Date date2) {

        long diff = date2.getTime() - date1.getTime();

        long diffMinutes = diff / (60 * 1000) % 60;
        long diffHours = diff / (60 * 60 * 1000) % 24;

        return diffHours;

    }

    public void viewAllStaffHoursReportsByMonth(Scanner scanner) throws Exception {
        try {
            System.out.println("Enter month to watch:");
            String month = scanner.nextLine();

            Set<StaffHour> staffHours = this.hoursReportController.getAllStaffHourByMonth(Integer.parseInt(month));
            printStaffHourList(staffHours);
        } catch (Exception ex) {
            System.out.println("Failed to view All Staff Hours Reports By Month");

        }
    }

    public void viewAllStaffHoursReportsToday() throws Exception {
        try {
            Date date = new Date();
            LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

            Set<StaffHour> staffHours = this.hoursReportController.getAllStaffHourToday(localDate);
            printStaffHourList(staffHours);

        } catch (Exception ex) {
            System.out.println("Failed to view All Staff Hours Reports today");

        }
    }

    public void paySalaryToStaffID(Scanner scanner, StaffView staffView, StaffController staffController) throws Exception {
        try {
            System.out.print("choose staff Id from list : ");
            staffView.viewAllStaff();
            String staffID = scanner.nextLine();

            System.out.println("Enter month to pay:");
            String month = scanner.nextLine();

            Staff staff = staffController.getStaffByID(Integer.parseInt(staffID));

            double totalSalary = getTotalSalary(staff, Integer.parseInt(month));
            String message = "hello " + staff.getFirstName() + " your salary from month " + month + " is " + totalSalary;
            message += ", The salary went into the account : " + staff.getBankDetails().toString();

            Mail.sendMail(message, staff.getMailAddress());

        } catch (Exception ex) {
            System.out.println("Failed to pay salary");

        }
    }

    private double getTotalSalary(Staff staff, int month) throws Exception {
        try {
            Set<StaffHour> staffHours = this.hoursReportController.getAllStaffHourByMonth(month);

            int totalHours = 0;
            int totalMin = 0;
            double totalSalary = 0;

            for (StaffHour staffHour : staffHours) {
                System.out.println(staffHour);
                totalHours += getHoursDifference(staffHour.getClockInDate(), staffHour.getClockOutDate());
                totalMin += getMinDifference(staffHour.getClockInDate(), staffHour.getClockOutDate());

            }
            System.out.println("total hours: " + totalHours + " Minutes: " + totalMin);
            System.out.println("total salary: " + totalSalary);

            totalSalary = totalHours * staff.getWage() + (totalMin / 60 * staff.getWage());

            return totalSalary;

        } catch (Exception ex) {
            System.out.println("Failed to get total salary");
            return 0;

        }
    }

    public void viewMyHoursReport(Staff staff) throws Exception {
        try {
            Set<StaffHour> staffHours = this.hoursReportController.getStaffHourBy(staff.getPersonId());
            printStaffHourList(staffHours);

        } catch (Exception ex) {
            System.out.println("Failed to view My Hours Report");

        }
    }


}

