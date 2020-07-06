package model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StaffHour implements Serializable {
    private int staffID;
    private int shiftNum;
    private Date clockInDate;
    private Date clockOutDate;


    public StaffHour(int staffID, int shiftID, Date clockIn) {
        this.staffID = staffID;
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

    public int getStaffID() {
        return staffID;
    }

    @Override
    public String toString() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        return "[staffID=" + this.staffID + ", shiftNum=" + this.shiftNum + ", clock In =" + formatter.format(this.clockInDate) + ", clock out =" + formatter.format(this.clockOutDate) + "]";
   }
}
