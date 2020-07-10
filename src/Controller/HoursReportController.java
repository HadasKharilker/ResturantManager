package Controller;

import model.*;
import model.Service.HoursReportService;

import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

public class HoursReportController {
    //for singelton
    private static HoursReportController INSTANCE;
    private static final Object lockObject = new Object();
    private final HoursReportService hoursReportService;


    //for singelton
    private HoursReportController() throws Exception {
        this.hoursReportService = new HoursReportService();
    }

    public static HoursReportController getInstance() throws Exception {
        if (INSTANCE == null) {
            synchronized (lockObject) {
                if (INSTANCE == null) {
                    INSTANCE = new HoursReportController();
                }
            }
        }

        return INSTANCE;
    }

    public boolean viewAllStaffHoursReportsToday() {
        return this.hoursReportService.viewAllStaffHoursReportsToday();
    }

    public boolean viewStaffHouerWage(String staffID) {
        return this.hoursReportService.viewStaffHouerWage(staffID);
    }

    public boolean viewMyHoursReport(Staff staff) {
        return this.hoursReportService.viewMyHoursReport(staff);
    }

    public boolean viewAllStaffHoursReportsByMonth(String month) {
        return this.hoursReportService.viewAllStaffHoursReportsByMonth(month);
    }

    public boolean paySalaryToStaffID(String staffID, String month) {
        return this.hoursReportService.paySalaryToStaffID(staffID, month);
    }

    public boolean clockOut(Staff staff, int shiftNum) {
        return hoursReportService.clockOut(staff, shiftNum);
    }

    public int clockIn(Staff staff) {
        return hoursReportService.clockIn(staff);
    }

}
