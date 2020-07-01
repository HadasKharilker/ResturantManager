package model;

import java.io.IOException;
import java.util.Set;

public interface EmployeeRepository {


    void addEmployee(Employee emp) throws Exception ;

    void deleteEmployee(int id) throws IOException;
    void editEmployeePersonalDetails(Employee emp) throws Exception;
    void updateEmployeeUserDetails(Employee emp) throws Exception;

    Employee find(int id);

    Set<Employee> findAll();
    //edit


}
