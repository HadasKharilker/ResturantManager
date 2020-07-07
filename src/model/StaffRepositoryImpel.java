package model;

import java.io.IOException;
import java.util.Set;

public class StaffRepositoryImpel implements StaffRepository {
    private static StaffRepositoryImpel INSTANCE ;
    private static Object lockObject = new Object();
    private final String FILENAME = "staff";
    private Set<Staff> staff;
    private FileManager<Staff> fileManager;

    //singelton needs a private constructor
    private StaffRepositoryImpel() throws IOException, ClassNotFoundException {
        this.fileManager = new FileManager<Staff>(FILENAME);
        this.staff = this.fileManager.read();

    }

    //for singelton use
    public static StaffRepositoryImpel getInstance() throws Exception{
       if( INSTANCE == null) {
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

        if (this.staff.contains(staff)) {
            throw new Exception("Already exists!");
        }

        this.staff.add(staff);
        this.fileManager.write(this.staff);
    }


    @Override
    public void deleteStaff(int id) throws Exception {
        if (!(this.staff.contains(new Staff(id)))) {
            throw new Exception("staff member does'nt exists!");
        }

        this.staff.remove(new Staff(id));
        this.fileManager.write(this.staff);

    }



    @Override
    public void editPersonDetails(Staff staff1) throws Exception {
        if (staff1 == null) {
            throw new Exception("must have a value");
        }
        if (!(this.staff.contains(staff1))) {
            throw new Exception("Staff does not exists!");
        } else {
            for (Staff s : staff) {
                if (s.getPersonId() == staff1.getPersonId()) {
                    s.setFirstName(staff1.getFirstName());
                    s.setLastName(staff1.getLastName());
                    s.setBirthDate(staff1.getBirthDate());
                    s.setAddress(staff1.getAddress());

                }
            }

        }

        this.fileManager.write(this.staff);

    }

    @Override
    public void editStaffDetails(Staff staff1) throws Exception {
        if (staff1 == null) {
            throw new Exception("must have a value");
        }
        if (!(this.staff.contains(staff1))) {
            throw new Exception("Staff does not exists!");
        } else {
            for (Staff s : staff) {
                if (s.getPersonId() == staff1.getPersonId()) {
                    s.setUserDetails(staff1.getUserDetails());

                }
            }

        }
        this.fileManager.write(this.staff);

    }


    @Override
    public Staff getStaffByID(int id) {

        for (Staff s : staff) {
            if (s.getPersonId() == id)
                return s;
        }
        return null;


    }

    @Override
    public Staff getStaffByUserName(String curUserName) {
        for (Staff s : staff) {
            UserDetails userDetails = s.getUserDetails();
            String userName = userDetails.getUserName();

            if (userDetails != null)
                if (userName.equals(curUserName))
                    return s;

        }


        return null;
    }


    @Override
    public Set<Staff> findAll() {

        return this.staff;

    }

    @Override
    public boolean  isExist(int id){
        return staff.contains(new Staff(id));




    }

}







