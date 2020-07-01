package model;
import java.io.Serializable;

public class UserDetails implements Serializable {
    private String userName;
    private String password;
    private Role role;
    private static final long serialVersionUID = 1L;


    public UserDetails(String userName,String password,Role role){
        super();
        this.userName=userName;
        this.password=password;
        this.role=role;

    }

    public UserDetails(String userName,String password){
        super();
        this.userName=userName;
        this.password=password;

    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
