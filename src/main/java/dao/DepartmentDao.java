package dao;

import services.Department;

public class DepartmentDao extends AbstractJpaDao<Long, Department>{

    public DepartmentDao() {
        super(Department.class);
    }
}
