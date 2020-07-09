package model.Repository;

import model.Staff;
import model.StaffHour;

import java.io.IOException;
import java.time.Month;
import java.util.Set;

public interface HoursReportRepository {
    int clockIn(Staff staff) throws IOException;

    void clockOut(Staff staff, int numberShift) throws IOException;

    Set<StaffHour> getAllStaffHour(int month) throws IOException;

    Set<StaffHour> getStaffHourBy(int staffID) throws IOException;



}
