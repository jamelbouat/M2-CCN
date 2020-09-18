package dao;

import jpa.EntityManagerHelper;
import services.Department;
import services.Employee;
import services.Section;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;

public class EmployeeDaoImpl implements EmployeeDao {

    @Override
    public Employee getEmployeeDao(long id) {

        EntityManager em = EntityManagerHelper.getEntityManager();
        String query = "SELECT employee FROM Employee employee WHERE employee.id = :employeeID";

        TypedQuery<Employee> tq = em.createQuery(query, Employee.class);
        tq.setParameter("employeeID", id);

        try {
            return tq.getSingleResult();

        } catch (NoResultException e) {
            throw new NoResultException("employee does not exist");

        } finally {
//            EntityManagerHelper.closeEntityManager();
//            EntityManagerHelper.closeEntityManagerFactory();
        }
    }

    @Override
    public void addEmployeeDao(String name, Department department) {

        try {
            EntityManagerHelper.beginTransaction();

            Employee employee = new Employee();
            employee.setName(name);
            employee.setDepartment(department);
            EntityManagerHelper.getEntityManager().persist(employee);
            EntityManagerHelper.commit();
        } catch (Exception e) {

            e.printStackTrace();
        } finally {
//            EntityManagerHelper.closeEntityManager();
//            EntityManagerHelper.closeEntityManagerFactory();
        }
    }

    @Override
    public void updateEmployeeDao(long id, String name) {

        EntityManager em = EntityManagerHelper.getEntityManager();
        Employee employee;
        try {
            EntityManagerHelper.beginTransaction();
            employee = em.find(Employee.class, id);
            employee.setName(name);
            EntityManagerHelper.getEntityManager().persist(employee);
            EntityManagerHelper.commit();

        } catch (Exception e) {

            e.printStackTrace();
        } finally {
//            EntityManagerHelper.closeEntityManager();
//            EntityManagerHelper.closeEntityManagerFactory();
        }
    }

    @Override
    public void removeEmployeeDao(long id) {

        EntityManager em = EntityManagerHelper.getEntityManager();
        Employee employee;
        try {
            EntityManagerHelper.beginTransaction();
            employee = em.find(Employee.class, id);
            em.remove(employee);
            EntityManagerHelper.commit();

        } catch (Exception e) {

            e.printStackTrace();
        } finally {
//            EntityManagerHelper.closeEntityManager();
//            EntityManagerHelper.closeEntityManagerFactory();
        }
    }

    @Override
    public List<Employee> getAllEmployeesDao() {
        EntityManager em = EntityManagerHelper.getEntityManager();

        String Query = "SELECT employee FROM Employee employee WHERE employee.id IS NOT NULL";
        TypedQuery<Employee> tq = em.createQuery(Query, Employee.class);

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
