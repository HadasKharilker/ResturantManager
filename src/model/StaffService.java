package model;
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

    public boolean editStaffPersonalDetails(Staff staff) {
        try {
            this.staffRepository.editPersonDetails(staff);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }
    public boolean editStaffUserDetails(Staff staff) {
        try {
            this.staffRepository.editStaffDetails(staff);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }





    public boolean isExist(Integer id) {
        return this.staffRepository.isExist(id);

        }
    }

