package Test;

import Controller.HoursReportController;
import Controller.OrderController;
import model.MenuItem;
import model.MenuItemType;
import model.StaffHour;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class HoursReportTest {
    private HoursReportController hoursReportController;

    @BeforeEach
    public void setup() throws Exception {
        hoursReportController = HoursReportController.getInstance();

    }


    @Test
    public void FailGetStaffHourByEmptyIdTest() {

        Assertions.assertThrows(Exception.class, () -> {

            Set<StaffHour> result = hoursReportController.getStaffHourBy(12);
            Assertions.assertNull(result);

        });
    }

    @Test

    public void FailgetAllStaffHourByEmptyMonthTest() {

        Assertions.assertThrows(Exception.class, () -> {

            Set<StaffHour> result = hoursReportController.getAllStaffHourByMonth(15);
            Assertions.assertNull(result);

        });
    }


}