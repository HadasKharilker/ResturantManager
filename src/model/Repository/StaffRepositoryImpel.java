package model.Repository;

import model.FileManager;
import model.Staff;
import model.UserDetails;

import java.io.IOException;
import java.util.Set;

public class StaffRepositoryImpel implements StaffRepository {
    private static StaffRepositoryImpel INSTANCE;
    private static Object lockObject = new Object();
    private final String FILENAME = "staff";
    private Set<Staff> staffSet;
    private FileManager<Staff> fileManager;

    //singelton needs a private constructor
    private StaffRepositoryImpel() throws IOException, ClassNotFoundException {
        this.fileManager = new FileManager<Staff>(FILENAME);
        this.staffSet = this.fileManager.read();

    }

    //for singelton use
    public static StaffRepositoryImpel getInstance() throws Exception {
        if (INSTANCE == null) {
            synchronized (lockObject) {
                if (INSTANCE == null) {
                    INSTANCE = new StaffRepositoryImpel();
                }
            }
        }
        return INSTANCE;
    }


    @Override
    public void addStaff(Staff staff) throws Exception {
        if (staff == null) {
            throw new Exception("must have a value");
        }

        if (this.staffSet.contains(staff)) {
            throw new Exception("Already exists!");
        }

        this.staffSet.add(staff);
        this.fileManager.write(this.staffSet);
    }


    @Override
    public void deleteStaff(int id) throws Exception {
        Staff staff = getStaffByID(id);

        if (!(this.staffSet.contains(staff))) {
            throw new Exception("staff member does'nt exists!");
        }

        this.staffSet.remove(staff);
        this.fileManager.write(this.staffSet);

    }


    @Override
    public void editStaff(Staff staff1) throws Exception {
        if (staff1 == null) {
            throw new Exception("must have a value");
        }
        if (!(this.staffSet.contains(staff1))) {
            throw new Exception("Staff does not exists!");
        } else {
            for (Staff s : staffSet) {
                if (s.getPersonId() == staff1.getPersonId()) {
                    staffSet.remove(s);
                    staffSet.add(s);
                    break;
                }
            }

        }

        this.fileManager.write(this.staffSet);

    }
    

    @Override
    public Staff getStaffByID(int id) {

        for (Staff s : staffSet) {
            if (s.getPersonId() == id)
                return s;
        }
        return null;


    }

    @Override
    public Staff getStaffByUserName(String curUserName) {
        for (Staff s : staffSet) {
            UserDetails userDetails = s.getUserDetails();
            String userName = userDetails.getUserName();

            if (userDetails != null)
                if (userName.equals(curUserName))
                    return s;

        }


        return null;
    }


    @Override
    public Set<Staff> getAllStaff() {

        return this.staffSet;

    }


}







