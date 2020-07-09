
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
            DateTimeFormatter formatter= DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.ENGLISH);
            LocalDate date = LocalDate.parse("12/03/1990", formatter);
            Address address=   new Address.AddressBuilder("12").houseNumber(Integer.parseInt("12")).city("test").state("test").build();

            boolean result = staffController.addNewStaff(new Employee(1, "Test", "Test", date, address, new UserDetails("test", "test", Role.valueOf("manager")), new BankDetails("99999", 99999), "test@test.com",EmployeeType.valueOf("waiter")));
            System.out.println(result);
            Assertions.assertFalse(result);


        });
    }



    @Test
    public void FailAddNewManagerWithOccupiedID() {

        Assertions.assertThrows(Exception.class, () -> {
            DateTimeFormatter formatter= DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.ENGLISH);
            LocalDate date = LocalDate.parse("12/03/1990", formatter);

         Address address=   new Address.AddressBuilder("12").houseNumber(Integer.parseInt("12")).city("test").state("test").build();

            boolean result = staffController.addNewStaff(new Manager(1, "Test", "Test", date, address, new UserDetails("test", "test", Role.valueOf("manager")), new BankDetails("99999", 99999), "test@test.com"));
            System.out.println(result);
            Assertions.assertFalse(result);


        });
    }


    @Test
    public void FailRemoveManagerWithInvalidID() {

        Assertions.assertThrows(Exception.class, () -> {
            LocalDate.parse("11/11/1990", DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.ENGLISH));

            boolean result = staffController.deleteStaff(29999);
            Assertions.assertFalse(result);


        });
    }

    @Test
        public void SuccessAddNewMinorEmployeeWithValidID() {

        Assertions.assertThrows(Exception.class, () -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.ENGLISH);
            LocalDate date = LocalDate.parse("12/03/1990", formatter);
            Address address = new Address.AddressBuilder("12").houseNumber(Integer.parseInt("12")).city("test").state("test").build();

            boolean result = staffController.addNewStaff(new Employee(1, "Test", "Test", date, address, new UserDetails("test", "test", Role.valueOf("manager")), new BankDetails("99999", 99999), "test@test.com", EmployeeType.valueOf("waiter")));
            System.out.println(result);
            Assertions.assertTrue(result);


        });
    }

        @Test
        public void SuccessAddNewShiftManagerEmployeeWithValidID() {

            Assertions.assertThrows(Exception.class, () -> {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.ENGLISH);
                LocalDate date = LocalDate.parse("12/03/1990", formatter);
                Address address = new Address.AddressBuilder("12").houseNumber(Integer.parseInt("12")).city("test").state("test").build();

                boolean result = staffController.addNewStaff(new Employee(1, "Test", "Test", date, address, new UserDetails("test", "test", Role.valueOf("manager")), new BankDetails("99999", 99999), "test@test.com", EmployeeType.valueOf("shiftManager")));
                System.out.println(result);
                Assertions.assertTrue(result);

            })


        ;}
}


















