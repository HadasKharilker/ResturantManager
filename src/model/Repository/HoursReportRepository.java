package model.Repository;

import model.Staff;
import model.StaffHour;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;
import java.util.Date;
import java.util.Set;

public interface HoursReportRepository {
    int clockIn(Staff staff) throws Exception;

    void clockOut(Staff staff, int numberShift) throws Exception;

    Set<StaffHour> getAllStaffHourByMonth(int month) throws Exception;

    Set<StaffHour> getStaffHourByStaffID(int staffID) throws Exception;

    Set<StaffHour> getAllStaffHourToday(LocalDate todayDate) throws Exception;

}
