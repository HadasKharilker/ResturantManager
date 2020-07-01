package model;
import java.io.IOException;

import java.util.Set;

public class EmployeeRepositoryImpel implements EmployeeRepository {

    private final String FILENAME = "employees";
    private Set<Employee> employees;
    private FileManager<Employee> fileManager;

    public EmployeeRepositoryImpel() throws IOException, ClassNotFoundException {
        this.fileManager = new FileManager<Employee>(FILENAME);
        this.employees = this.fileManager.read();

    }
    @Override
    public void addEmployee(Employee emp) throws Exception {
        if (emp == null) {
            throw new Exception("must have a value");
        }

        if (this.employees.contains(emp)) {
            throw new Exception("Already exists!");
        }

        this.employees.add(emp);

        this.fileManager.write(this.employees);
    }

    @Override
    public void deleteEmployee(int id) throws IOException {

            this.employees.remove(new Employee(id));
            this.fileManager.write(this.employees);

    }

    @Override
    public void editEmployeePersonalDetails(Employee emp) throws Exception {
        if (emp == null) {
            throw new Exception("must have a value");
        }
        if (!(this.employees.contains(emp))) {
            throw new Exception("Staff does not exists!");
        }
        else
        {
            for (Staff e :employees ) {
                if (e.getPersonId() == emp.getPersonId()) {
                    e.setFirstName(emp.getFirstName());
                    e.setLastName(emp.getLastName());
                    e.setBirthDate(emp.getBirthDate());
                    e.setAddress(emp.getAddress());

                }
            }

        }
        this.fileManager.write(this.employees);

    }













    @Override
    public void updateEmployeeUserDetails(Employee emp) throws Exception {
        if (emp == null) {
            throw new Exception("must have a value");
        }
        if (!(this.employees.contains(emp))) {
            throw new Exception("employee does not exists!");
        }
        else
        {
            for (Employee e : employees) {
                if (e.getPersonId() == emp.getPersonId()) {
                    e.setUserDetails(emp.getUserDetails());

                }
            }

        }
        this.fileManager.write(this.employees);

    }

    @Override
    public Employee find(int itemID) {
        if (this.employees.contains(new Employee(itemID))) {
            for (Employee m : employees) {
                if (m.getPersonId() == itemID) {
                    return m;
                }
            }
        }

        return null;
    }

    @Override
    public Set<Employee> findAll() {

        return this.employees;
    }

}





