package model.Service;

import model.*;
import model.Repository.StaffRepository;
import model.Repository.StaffRepositoryImpel;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Set;

public class StaffService {

    private final StaffRepository staffRepository;


    public StaffService() throws Exception {
        staffRepository = StaffRepositoryImpel.getInstance();
    }

    public boolean addNewEmployee(String personId, String fName, String lname, String birthDate, String houseNum,
                                  String houseStreet, String city, String username, String staffPassword, String role,
                                  String state, String bankAccountNumber, String brunchNumber, String mail, String employeeType) {
        try {
            Address address = new Address.AddressBuilder(houseStreet).houseNumber(Integer.parseInt(houseNum)).city(city).state(state).build();
            BankDetails bankDetails = new BankDetails(bankAccountNumber, Integer.parseInt(brunchNumber));

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.ENGLISH);
            LocalDate date = LocalDate.parse(birthDate, formatter);

            UserDetails userDetails = new UserDetails(username, staffPassword, Role.valueOf(role));

            Staff staff = new Employee(Integer.parseInt(personId), fName, lname, date, address, userDetails, bankDetails, mail, EmployeeType.valueOf(employeeType));
            this.staffRepository.addStaff(staff);
            return true;

        } catch (Exception e) {

            return false;
        }
    }

    public boolean addNewManager(String personId, String fName, String lname, String birthDate, String houseNum,
                                 String houseStreet, String city, String username, String staffPassword, String role,
                                 String state, String bankAccountNumber, String brunchNumber, String mail, String licenseNum) {
        try {
            Address address = new Address.AddressBuilder(houseStreet).houseNumber(Integer.parseInt(houseNum)).city(city).state(state).build();
            BankDetails bankDetails = new BankDetails(bankAccountNumber, Integer.parseInt(brunchNumber));

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.ENGLISH);
            LocalDate date = LocalDate.parse(birthDate, formatter);

            UserDetails userDetails = new UserDetails(username, staffPassword, Role.valueOf(role));

            Staff staff = new Manager(Integer.parseInt(personId), fName, lname, date, address, userDetails, bankDetails, mail, licenseNum);
            this.staffRepository.addStaff(staff);
            return true;

        } catch (Exception e) {

            return false;
        }
    }

    public boolean deleteStaff(String staffID) {
        try {
            this.staffRepository.deleteStaff(Integer.parseInt(staffID));
            return true;
        } catch (Exception e) {

            return false;
        }
    }

    public boolean editStaff(String staffId, String fName, String lname, String birthDate, String houseNum, String houseStreet, String city,
                             String state, String bankAccountNumber, String brunchNumber, String mail) {
        try {

            Staff staff = getStaffByID(Integer.parseInt(staffId));

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.ENGLISH);
            LocalDate date = LocalDate.parse(birthDate, formatter);

            Address address = new Address.AddressBuilder(houseStreet).houseNumber(Integer.parseInt(houseNum)).city(city).state(state).build();
            BankDetails bankDetails = new BankDetails(bankAccountNumber, Integer.parseInt(brunchNumber));

            staff.setFirstName(fName);
            staff.setLastName(lname);
            staff.setBirthDate(date);
            staff.setAddress(address);
            staff.setBankDetails(bankDetails);
            staff.setMailAddress(mail);

            this.staffRepository.editStaff(staff);

            return true;

        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }


    }

    public boolean editStaff(String staffID, String role, String username, String staffPassword) {
        try {

            Staff staff = getStaffByID(Integer.parseInt(staffID));

            UserDetails userDetails = new UserDetails(username, staffPassword, Role.valueOf(role));
            staff.setUserDetails(userDetails);

            this.staffRepository.editStaff(staff);

            return true;

        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }


    }


    public boolean printAllStaff() {
        try {
            Set<Staff> staffSet = this.staffRepository.getAllStaff();
            System.out.println("List of Staff members:");
            for (Staff s : staffSet) {
                System.out.println(s);

            }

            return true;

        } catch (Exception ex) {
            return false;
        }
    }


    public Staff getStaffByID(int id) {

        try {
            return this.staffRepository.getStaffByID(id);

        } catch (Exception e) {

            return null;
        }
    }

}

