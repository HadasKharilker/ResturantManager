package Controller;

import model.LoginService;

//singelton
public class LoginController {


    private static LoginController INSTANCE;
    private static final Object lockObject = new Object();

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





    public Integer login(String username, String password) throws Exception{
        if (username == null || username.trim().equals("") || password == null || password.trim().equals("")) {
            throw new IllegalArgumentException("Username or password must not be null");
        }

        Integer typeOfLogin = loginService.typeOfLogin(username, password);


        return typeOfLogin;
    }

}
