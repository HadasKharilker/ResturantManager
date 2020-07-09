package Controller;

import model.*;
import model.Service.HoursReportService;

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

    public Set<StaffHour> getStaffHourBy(int staffID) {
        return hoursReportService.getStaffHourBy(staffID);

    }

    public boolean clockOut(Staff staff, int shiftNum) {
        return hoursReportService.clockOut(staff, shiftNum);
    }

    public int clockIn(Staff staff) {
        return hoursReportService.clockIn(staff);
    }

    public Set<StaffHour> getAllStaffHour(int month) {
        return hoursReportService.getAllStaffHour(month);
    }

}
