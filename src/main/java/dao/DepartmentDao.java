package dao;

import services.Department;
import services.Section;

import java.util.List;

public interface DepartmentDao {
    public Department getDepartmentDao(long id);
    public void addDepartmentDao(String name);
    public void updateDepartmentDao(long id, String name);
    public void removeDepartmentDao(long id);
    List<Department> getAllDepartmentsDao();
}