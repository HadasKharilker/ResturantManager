package model;

import java.io.IOException;
import java.util.Set;

public class StaffRepositoryImpel implements StaffRepository {
    private final String FILENAME = "staff";
    private Set<Staff> staff;
    private FileManager<Staff> fileManager;

    public StaffRepositoryImpel() throws IOException, ClassNotFoundException {
        this.fileManager = new FileManager<Staff>(FILENAME);
        this.staff = this.fileManager.read();
        System.out.println(this.staff);
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
    public void deleteStaff(int id) throws IOException {
        this.staff.remove(new Staff(id));
        this.fileManager.write(this.staff);

    }
/*
    @Override
    public void updateStaff(Staff curStaff) throws Exception
    {
        if (curStaff == null) {
            throw new Exception("must have a value");
        }
        if (!(this.staff.contains(curStaff))) {
            throw new Exception("staff does not exists!");
        }
        else
        {
            for (Staff s : staff) {
                if (s.getPersonId() == curStaff.getPersonId())
                {
                    s.setAddress(curStaff.getAddress());
                    s.set(curStaff.getAddress());
                    s.setAddress(curStaff.getAddress());

                    s.setName(menuItem.getItemName());
                    s.setPrice(menuItem.getPrice());
                    s.setItemType(menuItem.getItemType());

                }
            }

        }
        this.fileManager.write(this.menu);

    }*/


    @Override
    public void editPersonDetails(Staff staff1) throws Exception {
        if (staff1 == null) {
            throw new Exception("must have a value");
        }
        if (!(this.staff.contains(staff1))) {
            throw new Exception("Staff does not exists!");
        }
        else
        {
            for (Staff s :staff ) {
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
        }
        else
        {
            for (Staff s :staff ) {
                if (s.getPersonId() == staff1.getPersonId()) {
                    s.setUserDetails(staff1.getUserDetails());

                }
            }

        }
        this.fileManager.write(this.staff);

    }




    @Override
    public Staff getStaffByID(int id) {

        for (Staff s:staff)
        {
            if (s.getPersonId() == id)
                return s;
        }
            return null;


    }
    @Override
    public Staff getStaffByUserName(String userName)
    {
        for (Staff s : staff)
        {
            if (s.getUserDetails().getUserName() == userName)
                return s;

        }


    return null;
    }




    @Override
    public Set<Staff> findAll() {

        return this.staff;

    }




}







