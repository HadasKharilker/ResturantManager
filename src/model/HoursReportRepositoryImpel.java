package model;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class HoursReportRepositoryImpel implements HoursReportRepository {    //for singelton
    private static HoursReportRepositoryImpel INSTANCE;
    private static Object lockObject = new Object();

    private final String FILENAME = "hoursReport";
    private Set<StaffHour> staffHours;
    private FileManager<StaffHour> fileManager;

    //singelton has a private constructor
    private HoursReportRepositoryImpel() throws IOException, ClassNotFoundException {
        this.fileManager = new FileManager<StaffHour>(FILENAME);
        this.staffHours = this.fileManager.read();
    }

    //for singelton use
    public static HoursReportRepositoryImpel getInstance() throws Exception {
        if (INSTANCE == null) {
            synchronized (lockObject) {
                if (INSTANCE == null) {
                    INSTANCE = new HoursReportRepositoryImpel();
                }
            }
        }
        return INSTANCE;
    }

    @Override
    public int clockIn(Staff staff) throws IOException {

        int shiftNum = getNewShiftNumTo(staff.getPersonId());
        StaffHour staffHour = new StaffHour(staff, shiftNum, new Date());

        this.staffHours.add(staffHour);
        this.fileManager.write(this.staffHours);

        return shiftNum;

    }

    private int getNewShiftNumTo(int staffID) {

        int maxShiftNum = 0;
        for (StaffHour staffHour : staffHours) {
            if (staffHour.getStaff().getPersonId() == staffID) {
                if (staffHour.getShiftNum() > maxShiftNum)
                    maxShiftNum = staffHour.getShiftNum();
            }
        }

        return maxShiftNum + 1;
    }

    @Override
    public void clockOut(Staff staff, int numberShift) throws IOException {

        for (StaffHour staffHour : staffHours) {
            if (staffHour.getStaff().getPersonId() == staff.getPersonId() && staffHour.getShiftNum() == numberShift) {
                staffHour.setClockOutDate(new Date());
            }
        }
        this.fileManager.write(this.staffHours);

    }


    @Override
    public Set<StaffHour> getAllStaffHour(int month) throws IOException {

        Set<StaffHour> spesificStaffHour = new HashSet<StaffHour>();

        for (StaffHour staffHour : staffHours) {
            LocalDate localDate = staffHour.getClockInDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            int shMonth = localDate.getMonthValue();

            if (shMonth == month)
                spesificStaffHour.add(staffHour);
        }

        return spesificStaffHour;
    }

    @Override
    public Set<StaffHour> getStaffHourBy(int staffID) throws IOException {
        Set<StaffHour> spesificStaffHour = new HashSet<StaffHour>();

        for (StaffHour staffHour : staffHours) {
            if (staffHour.getStaff().getPersonId() == staffID)
                spesificStaffHour.add(staffHour);
        }

        return spesificStaffHour;
    }


}
