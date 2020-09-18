package dao;

import services.Department;
import services.Employee;

import java.util.List;

public interface EmployeeDao {
    public Employee getEmployeeDao(long id);
    public void addEmployeeDao(String name, Department department);
    public void updateEmployeeDao(long id, String name);
    public void removeEmployeeDao(long id);
    public List<Employee> getAllEmployeesDao();
}
