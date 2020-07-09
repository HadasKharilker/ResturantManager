package model.Repository;

import model.Staff;
import model.StaffHour;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;
import java.util.Date;
import java.util.Set;

public interface HoursReportRepository {
    int clockIn(Staff staff) throws IOException;

    void clockOut(Staff staff, int numberShift) throws IOException;

    Set<StaffHour> getAllStaffHourByMonth(int month) throws IOException;

    Set<StaffHour> getStaffHourByStaffID(int staffID) throws IOException;

    Set<StaffHour> getAllStaffHourToday(LocalDate todayDate) throws IOException;

}
