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
        if (staffID != "")
            return this.hoursReportService.viewStaffHouerWage(staffID);

        return false;
    }

    public boolean viewMyHoursReport(Staff staff) {
        if (staff != null)
            return this.hoursReportService.viewMyHoursReport(staff);

        return false;
    }

    public boolean viewAllStaffHoursReportsByMonth(String month) {
        if (month != "")
            return this.hoursReportService.viewAllStaffHoursReportsByMonth(month);

        return false;
    }

    public boolean paySalaryToStaffID(String staffID, String month) {
        if (month != "" && staffID != "")
            return this.hoursReportService.paySalaryToStaffID(staffID, month);

        return false;
    }

    public boolean clockOut(Staff staff, int shiftNum) {
        if (shiftNum != 0 && staff != null)
            return hoursReportService.clockOut(staff, shiftNum);

        return false;
    }

    public int clockIn(Staff staff) {
        if (staff != null)
            return hoursReportService.clockIn(staff);

        return 0;
    }

}
