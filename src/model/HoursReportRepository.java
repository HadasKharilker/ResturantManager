package model;

import java.io.IOException;
import java.time.Month;
import java.util.Set;

public interface HoursReportRepository {
    int clockIn(int staffID) throws IOException;

    void clockOut(int staffID, int numberShift) throws IOException;

    Set<StaffHour> getAllStaffHour(int month) throws IOException;

    Set<StaffHour> getStaffHourBy(int staffID) throws IOException;



}
