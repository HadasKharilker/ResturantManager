package Controller;

import View.HoursReportView;
import View.RestaurantView;
import model.Service.LoginService;
import model.Staff;

//singelton
public class LoginController {


    private static LoginController INSTANCE;
    private static final Object lockObject = new Object();
    private boolean isClockOut = false;
    private int shiftNum;

    private final LoginService loginService;
    //signup
    //forgot password
    //logout

    //for singelton
    private LoginController()throws Exception {
        this.loginService = new LoginService();
    }

    //for singelton
    public static LoginController getInstance() throws Exception {
        if (INSTANCE == null) {
            synchronized (lockObject) {
                if (INSTANCE == null) {
                    INSTANCE = new LoginController();
                }
            }
        }

        return INSTANCE;
    }

    public Staff getStaffByUserName(String username) throws Exception{
        return loginService.getStaffByUserName(username);


    }





    public boolean login(String username, String password, HoursReportView hoursReportView, RestaurantView resturantView) throws Exception{
        if (username == null || username.trim().equals("") || password == null || password.trim().equals("")) {
            throw new IllegalArgumentException("Username or password must not be null");
        }
        boolean isUserCorrect = false;


        Staff staff = this.getStaffByUserName(username);

        if (staff != null)
            isUserCorrect = staff.isPasswordCorrect(password);

        if (isUserCorrect) {
            isClockOut = false;

            shiftNum = hoursReportView.clockIn(staff);
            return true;



        } else

            return false;





    }

}
