package model.Service;
import model.Repository.StaffRepository;
import model.Repository.StaffRepositoryImpel;
import model.Staff;

import java.util.Set;

public class StaffService {

    private final StaffRepository staffRepository;


    public StaffService() throws Exception{
        staffRepository = StaffRepositoryImpel.getInstance();
    }



    public boolean addNewStaff(Staff staff){
        try {
            this.staffRepository.addStaff(staff);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Set<Staff> getAllStaff() {
        return this.staffRepository.findAll();
    }


    public boolean deleteStaff(Integer item) {
        try {
            this.staffRepository.deleteStaff(item);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean editStaff(Staff staff) {
        try {
            this.staffRepository.editStaff(staff);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }


    public Staff getStaffByID(int id) {

        try {
            return this.staffRepository.getStaffByID(id);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }


    }





    public boolean isExist(Integer id) {
        return this.staffRepository.isExist(id);

        }
    }

