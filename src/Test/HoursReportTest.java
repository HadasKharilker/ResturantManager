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
    public void FailGetStaffHourWageByEmptyIdTest() {

        Assertions.assertThrows(Exception.class, () -> {

            boolean result = hoursReportController.viewStaffHouerWage("");
            Assertions.assertFalse(result);

        });
    }

    @Test

    public void FailgetAllStaffHourByEmptyMonthTest() {

        Assertions.assertThrows(Exception.class, () -> {

            boolean result = hoursReportController.viewAllStaffHoursReportsByMonth("");
            Assertions.assertNull(result);

        });
    }
    @Test

    public void FailgetAllStaffHourByWrongMonthTest() {

        Assertions.assertThrows(Exception.class, () -> {

            boolean result = hoursReportController.viewAllStaffHoursReportsByMonth("22");
            Assertions.assertNull(result);

        });
    }



}