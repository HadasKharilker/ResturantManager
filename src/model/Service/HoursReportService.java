package model.Service;

import Controller.StaffController;
import View.StaffView;
import model.Mail;
import model.Repository.HoursReportRepository;
import model.Repository.HoursReportRepositoryImpel;
import model.Repository.StaffRepository;
import model.Repository.StaffRepositoryImpel;
import model.Staff;
import model.StaffHour;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Scanner;
import java.util.Set;

public class HoursReportService {
    private final HoursReportRepository hoursReportRepository;
    private final StaffRepository staffRepository;

    public HoursReportService() throws Exception {
        this.hoursReportRepository = HoursReportRepositoryImpel.getInstance();
        this.staffRepository = StaffRepositoryImpel.getInstance();
    }

    public Set<StaffHour> getStaffHourByStaffID(int staffID) {
        try {
            return this.hoursReportRepository.getStaffHourByStaffID(staffID);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public boolean paySalaryToStaffID(String staffID, String month) {
        try {

            Staff staff = this.staffRepository.getStaffByID(Integer.parseInt(staffID));

            double totalSalary = getTotalSalary(staff, Integer.parseInt(month));
            String message = "hello " + staff.getFirstName() + " your salary from month " + month + " is " + totalSalary;
            message += ", The salary went into the account : " + staff.getBankDetails().toString();

            Mail.sendMail(message, staff.getMailAddress());

            return true;

        } catch (Exception ex) {
            ex.printStackTrace();
            return false;

        }
    }

    public boolean viewAllStaffHoursReportsToday() {
        try {
            Date date = new Date();
            LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

            Set<StaffHour> staffHours = this.hoursReportRepository.getAllStaffHourToday(localDate);
            printStaffHourList(staffHours);

            return true;

        } catch (Exception ex) {
            ex.printStackTrace();
            return false;

        }
    }

    public void printStaffHourList(Set<StaffHour> staffHours) {

        int totalHours = 0;
        int totalMin = 0;

        for (StaffHour staffHour : staffHours) {
            System.out.println(staffHour);
            totalHours += getHoursDifference(staffHour.getClockInDate(), staffHour.getClockOutDate());
            totalMin += getMinDifference(staffHour.getClockInDate(), staffHour.getClockOutDate());
        }
        System.out.println("total hours: " + totalHours + " Minutes: " + totalMin);
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

    private double getTotalSalary(Staff staff, int month) throws Exception {

        Set<StaffHour> staffHours = hoursReportRepository.getAllStaffHourByMonth(month);

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
    }

    public boolean viewStaffHouerWage(String staffID) {
        try {

            Set<StaffHour> staffHours = this.hoursReportRepository.getStaffHourByStaffID(Integer.parseInt(staffID));
            printStaffHourList(staffHours);
            return true;

        } catch (Exception ex) {
            ex.printStackTrace();
            return false;


        }
    }


    public boolean viewAllStaffHoursReportsByMonth(String month) {
        try {
            Set<StaffHour> staffHours = this.hoursReportRepository.getAllStaffHourByMonth(Integer.parseInt(month));
            printStaffHourList(staffHours);
            return true;

        } catch (Exception ex) {
            ex.printStackTrace();
            return false;

        }
    }

    public boolean viewMyHoursReport(Staff staff) {
        try {
            Set<StaffHour> staffHours = this.hoursReportRepository.getStaffHourByStaffID(staff.getPersonId());
            printStaffHourList(staffHours);
            return true;

        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean clockOut(Staff staff, int shiftNum) {
        try {
            this.hoursReportRepository.clockOut(staff, shiftNum);
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    public int clockIn(Staff staff) {
        try {
            return this.hoursReportRepository.clockIn(staff);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }

    }


}
