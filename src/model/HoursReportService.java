package model;

import java.util.Set;

public class HoursReportService {
    private final HoursReportRepository hoursReportRepository;


    public HoursReportService() throws Exception {
        this.hoursReportRepository = HoursReportRepositoryImpel.getInstance();
    }

    public Set<StaffHour> getStaffHourBy(int staffID) {
        try {
            return this.hoursReportRepository.getStaffHourBy(staffID);

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

    public Set<StaffHour> getAllStaffHour(int month) {
        try {
            return this.hoursReportRepository.getAllStaffHour(month);


        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
}
