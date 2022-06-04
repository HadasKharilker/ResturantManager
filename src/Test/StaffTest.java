
package Test;


import Controller.StaffController;

import model.*;
import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import model.Role;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

public class StaffTest {


    private StaffController staffController;
    private static Staff staff;


    @BeforeEach
    public void setup() throws Exception {
        staffController = staffController.getInstance();

    }

    @AfterAll
    public void tearDown() {
        System.out.println("StaffTestsEnded");
    }


    @Test
//test 1: adding new employee with invalid menuItemID
    public void FailAddNewMinorEmployeeWithOccupiedID() {

        Assertions.assertThrows(Exception.class, () -> {
            boolean result = staffController.addNewEmployee("1", "Test", "Test", "12/03/1990", "12", "12", "test", "test", "test", "manager", "manager", "99999", "99999", "test@test.com", "waiter");

            System.out.println(result);
            Assertions.assertFalse(result);


        });
    }


    @Test
    public void FailAddNewManagerWithOccupiedID() {

        Assertions.assertThrows(Exception.class, () -> {
            boolean result = staffController.addNewManager("1", "Test", "Test", "12/03/1990", "12", "12", "test", "test", "test", "manager", "manager", "99999", "99999", "test@test.com", "1234");

            System.out.println(result);
            Assertions.assertFalse(result);


        });
    }


    @Test
    public void FailRemoveManagerWithInvalidID() {

        Assertions.assertThrows(Exception.class, () -> {

            boolean result = staffController.deleteStaff("29999");
            Assertions.assertFalse(result);


        });
    }

    @Test
    public void SuccessAddNewMinorEmployeeWithValidID() {

        Assertions.assertThrows(Exception.class, () -> {
            boolean result = staffController.addNewEmployee("1", "Test", "Test", "12/03/1990", "12", "12", "test", "test", "employee", "test", "manager", "99999", "99999", "test@test.com", "waiter");

            System.out.println(result);
            Assertions.assertTrue(result);
        });
    }

    @Test
    public void SuccessAddNewShiftManagerEmployeeWithValidID() {

        Assertions.assertThrows(Exception.class, () -> {
            boolean result = staffController.addNewEmployee("1", "Test", "Test", "12/03/1990", "12", "12", "test", "test", "test", "employee", "manager", "99999", "99999", "test@test.com", "shiftManager");
            System.out.println(result);
            Assertions.assertTrue(result);

        })


        ;
    }
}


















