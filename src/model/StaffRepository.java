package model;

import java.io.IOException;
import java.util.Set;

public interface StaffRepository {


        void addStaff(Staff staff) throws Exception ;

        void deleteStaff(int id) throws Exception;

        void editPersonDetails(Staff staff) throws Exception;

        void editStaffDetails(Staff staff1) throws Exception;

        Staff getStaffByID(int id);

        Set<Staff> findAll();

        boolean isExist(int id);

        Staff getStaffByUserName(String userName)throws Exception;

        //edit



    }

