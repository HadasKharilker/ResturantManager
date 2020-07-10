package Test;

import Controller.LoginController;
import Controller.MenuController;
import View.HoursReportView;
import View.RestaurantView;
import model.MenuItem;
import model.MenuItemType;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;

import org.junit.jupiter.api.BeforeEach;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

//testing functions in menuController
public class MenuTest {

    private MenuController menuController;
    private static MenuItem menuItem;


    @BeforeEach
    public void setup() throws Exception {
        menuController = MenuController.getInstance();

    }

    @AfterAll
    public void tearDown() {
        System.out.println("MenuTestsEnded");
    }


    @Test
//test 1: adding new menuItem with invalid menuItemID
    public void addMenuItemWithInvalidMenuitemID() {

        Assertions.assertThrows(Exception.class, () -> {

            boolean result = menuController.addMenuItem("99999", "xxx", "12.0", "manager");
            System.out.println(result);
            Assertions.assertFalse(result);

        });
    }


    @Test
    //test 2: adding new menuItem with menuItemType
    public void addMenuItemWithInvalidMenuitemType() {

        Assertions.assertThrows(Exception.class, () -> {

            boolean result = menuController.addMenuItem("1", "xxx", "12.0", "koko");
            System.out.println(result);
            Assertions.assertFalse(result);

        });
    }


    @Test
    //test 3: Checking success  valid menuItem addition
    public void addValidMenuItem() throws Exception {
        Assertions.assertThrows(Exception.class, () -> {

            boolean result = menuController.addMenuItem("15", "TOFI", "12.0", "DESERT");
            System.out.println(result);
            Assertions.assertTrue(result);

        });
    }

    @Test
    //test 4: delete valid menuItem addition
    public void RemoveValidMenuItem() throws Exception {
        Assertions.assertThrows(Exception.class, () -> {

            boolean result = menuController.deleteMenuItem("15");
            System.out.println(result);
            Assertions.assertTrue(result);

        });
    }


}



