package model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StaffHour implements Serializable {
    private Staff staff;
    private int shiftNum;
    private Date clockInDate;
    private Date clockOutDate;


    public StaffHour(Staff staff, int shiftID, Date clockIn) {
        this.staff = staff;
        this.clockInDate = clockIn;
        this.clockOutDate = clockIn;
        this.shiftNum = shiftID;
    }

    public void setClockOutDate(Date clockOutDate) {
        this.clockOutDate = clockOutDate;
    }

    public void setShiftNum(int shiftNum) {
        this.shiftNum = shiftNum;
    }

    public Date getClockInDate() {
        return clockInDate;
    }

    public Date getClockOutDate() {
        return clockOutDate;
    }

    public int getShiftNum() {
        return shiftNum;
    }

    public Staff getStaff() {
        return staff;
    }

    @Override
    public String toString() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        return "[staff ID=" + this.staff.getPersonId() + " staff Name=" + this.staff.getFirstName() + "" + this.staff.getLastName() + ", shift Num=" + this.shiftNum + ", clock In =" + formatter.format(this.clockInDate) + ", clock out =" + formatter.format(this.clockOutDate) + "]";
    }
}
