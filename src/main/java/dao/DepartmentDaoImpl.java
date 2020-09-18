package dao;

import jpa.EntityManagerHelper;
import services.Department;
import services.Employee;
import services.Section;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;

public class DepartmentDaoImpl implements DepartmentDao {

    @Override
    public Department getDepartmentDao(long id) {
        EntityManager em = EntityManagerHelper.getEntityManager();
        String query = "SELECT department FROM Department department WHERE department.id = :departmentID";

        TypedQuery<Department> tq = em.createQuery(query, Department.class);
        tq.setParameter("departmentID", id);

        try {
            return tq.getSingleResult();

        } catch (NoResultException e) {
            throw new NoResultException("department does not exist");

        } finally {
//            EntityManagerHelper.closeEntityManager();
//            EntityManagerHelper.closeEntityManagerFactory();
        }
    }

    @Override
    public void addDepartmentDao(String name) {

        try {
            EntityManagerHelper.beginTransaction();

            Department department = new Department();
            department.setName(name);
            EntityManagerHelper.getEntityManager().persist(department);
            EntityManagerHelper.commit();
        } catch (Exception e) {

            e.printStackTrace();
        } finally {
//            EntityManagerHelper.closeEntityManager();
//            EntityManagerHelper.closeEntityManagerFactory();
        }
    }

    @Override
    public void updateDepartmentDao(long id, String name) {
        EntityManager em = EntityManagerHelper.getEntityManager();
        Department department;

        try {
            EntityManagerHelper.beginTransaction();
            department = em.find(Department.class, id);
            department.setName(name);
            EntityManagerHelper.getEntityManager().persist(department);
            EntityManagerHelper.commit();

        } catch (Exception e) {

            e.printStackTrace();
        } finally {
//            EntityManagerHelper.closeEntityManager();
//            EntityManagerHelper.closeEntityManagerFactory();
        }
    }

    @Override
    public void removeDepartmentDao(long id) {
        EntityManager em = EntityManagerHelper.getEntityManager();
        Department department;

        try {
            EntityManagerHelper.beginTransaction();
            department = em.find(Department.class, id);
            em.remove(department);
            EntityManagerHelper.commit();

        } catch (Exception e) {

            e.printStackTrace();
        } finally {
//            EntityManagerHelper.closeEntityManager();
//            EntityManagerHelper.closeEntityManagerFactory();
        }
    }

    @Override
    public List<Department> getAllDepartmentsDao() {
        EntityManager em = EntityManagerHelper.getEntityManager();

        String Query = "SELECT department FROM Department department WHERE department.id IS NOT NULL";
        TypedQuery<Department> tq = em.createQuery(Query, Department.class);

        try {
            return tq.getResultList();

        } catch (NoResultException e) {

            throw new NoResultException("problem with the database");
        } finally {
//            EntityManagerHelper.closeEntityManager();
//            EntityManagerHelper.closeEntityManagerFactory();
        }
    }
}
