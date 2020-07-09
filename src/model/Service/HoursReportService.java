package model.Service;

import model.Repository.HoursReportRepository;
import model.Repository.HoursReportRepositoryImpel;
import model.Staff;
import model.StaffHour;

import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

public class HoursReportService {
    private final HoursReportRepository hoursReportRepository;


    public HoursReportService() throws Exception {
        this.hoursReportRepository = HoursReportRepositoryImpel.getInstance();
    }

    public Set<StaffHour> getStaffHourByStaffID(int staffID) {
        try {
            return this.hoursReportRepository.getStaffHourByStaffID(staffID);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
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

    public Set<StaffHour> getAllStaffHourByMonth(int month) {
        try {
            return this.hoursReportRepository.getAllStaffHourByMonth(month);


        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public Set<StaffHour> getAllStaffHourToday(LocalDate todayDate) {
        try {
            return this.hoursReportRepository.getAllStaffHourToday(todayDate);


        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
}
