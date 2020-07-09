package Test;

import Controller.LoginController;
import View.HoursReportView;
import View.MenuView;
import View.RestaurantView;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

//testing functions in loginController
public class LoginTest {


    @Test

    public void failLoginEmptyUserNameTest() throws Exception {
        LoginController loginController = LoginController.getInstance();
        HoursReportView hoursReportView=new HoursReportView();
        RestaurantView resturantView=new RestaurantView();


        try {
            loginController.login("", "123", hoursReportView, resturantView);
            fail("login success when should failed");
        } catch (IllegalArgumentException e) {
            Assert.assertEquals("Username or password must not be null", e.getMessage());

        }
    }

    @Test
    public void failLoginEmptyPasswordTest() throws Exception {
        LoginController loginController = LoginController.getInstance();
        HoursReportView hoursReportView=new HoursReportView();
        RestaurantView resturantView=new RestaurantView();



        try {
            loginController.login("", "123",hoursReportView, resturantView);
            fail("login success when should failed");
        } catch (IllegalArgumentException e) {
            Assert.assertEquals("Username or password must not be null", e.getMessage());

        }
    }

    @Test
    public void failLoginUserNameNotExistTest() throws Exception {
        LoginController loginController = LoginController.getInstance();
        HoursReportView hoursReportView=new HoursReportView();
        RestaurantView resturantView=new RestaurantView();


           boolean result= loginController.login("NOTVAILD", "123",hoursReportView,resturantView);
            Assert.assertFalse(result);

        }




    @Test
    public void failLoginUserWrongPasswordTest() throws Exception {
        LoginController loginController = LoginController.getInstance();
        HoursReportView hoursReportView=new HoursReportView();
        RestaurantView resturantView=new RestaurantView();


        boolean result= loginController.login("hadas", "123",hoursReportView,resturantView);
        Assert.assertFalse(result);

    }

    @Test
    public void SuccessLoginTest() throws Exception {
        LoginController loginController = LoginController.getInstance();
        HoursReportView hoursReportView=new HoursReportView();
        RestaurantView resturantView=new RestaurantView();


        boolean result= loginController.login("hadas", "1212",hoursReportView,resturantView);
        Assert.assertTrue(result);

    }


}









