package model.Service;


import model.Staff;
import model.Repository.StaffRepository;
import model.Repository.StaffRepositoryImpel;

public class LoginService {

    private StaffRepository staffRepository;

    public LoginService() throws Exception {
        staffRepository = StaffRepositoryImpel.getInstance();
    }

    public Staff getStaffByUserName(String userName) throws Exception{
        return staffRepository.getStaffByUserName(userName);


    }




    public Integer typeOfLogin(String userName, String password) throws Exception {

        Staff staff = staffRepository.getStaffByUserName(userName);

        boolean isUserCorrect = false;

        if (staff != null) {
            isUserCorrect = staff.isPasswordCorrect(password);
            if(isUserCorrect){
              if(staff.isManager())
                  return 1;
              else if(staff.isEmployee())
                      return 0;

            }


        }
        return -1;

    }


}



