package jpa;

import dao.*;
import services.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class JpaTest {

    /**
     * @param args
     */
    private static SectionDao sectionDao;
    private static DepartmentDao departmentDao;
    private static EmployeeDao employeeDao;
    private static CardDaoImp cardDao;


    public static void main(String[] args) {
//        EntityManagerHelper.rollback();
        String[] SECTIONS_NAMES = new String[]{"to do", "in progress", "done"};
        String[] DEPARTMENTS_NAMES = new String[]{"Engineering", "Assembling", "Validation"};
        String[] EMPLOYEES_NAMES = new String[]{"John", "Wong", "Marie", "Sami", "Julie"};
        String[] CARDS_LABELS = new String[]{"task-1", "task-2", "task-3", "task-4", "task-5", "task-6", "task-7", "task-8"};

        sectionDao = new SectionDaoImpl();
        departmentDao = new DepartmentDaoImpl();
        departmentDao = new DepartmentDaoImpl();
        employeeDao = new EmployeeDaoImpl();
        cardDao = new CardDaoImp();

        try {

            createSections(SECTIONS_NAMES);
            createDepartments(DEPARTMENTS_NAMES);
            List<Section> sections = sectionDao.getAllSectionsDao();
            List<Department> departments = departmentDao.getAllDepartmentsDao();
            createEmployees(EMPLOYEES_NAMES, departments);
            List<Employee> employees = employeeDao.getAllEmployeesDao();

            createCards(CARDS_LABELS, employees, sections);
            List<Card> cards = cardDao.getAllCards();

            new Kanban("kanban", sections);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            EntityManagerHelper.closeEntityManager();
            EntityManagerHelper.closeEntityManagerFactory();
        }
    }

    private static void createCards(String[] cardsLabels, List<Employee> employees, List<Section> sections) {
        List<Date> dates = new ArrayList<>();
        dates.add(new Date(2020,2,1));
        dates.add(new Date(2055,8,13));
        dates.add(new Date(2030,10,14));

        for (String label : cardsLabels) {
            int d = (int) (Math.random() * dates.size());
            int e = (int) (Math.random() * employees.size());
            int s = (int) (Math.random() * sections.size());
            int duration = (int) (Math.random() * 100);
            
            cardDao.addCard(label,
                    "dates.get(0)",
                    employees.get(e),
                    duration,
                    "tag-"+ Integer.toString(duration),
                    sections.get(s),
                    "company.com"
                    );
        }
    }

    private static void createEmployees(String[] employeesNames, List<Department> departments) {
        int i;
        for (String name : employeesNames) {
            i = (int) (Math.random() * departments.size());
            employeeDao.addEmployeeDao(name, departments.get(i));
        }
    }

    private static void createDepartments(String[] departmentsNames) {
        for (String name : departmentsNames) {
            departmentDao.addDepartmentDao(name);
        }
    }

    private static void createSections(String[] sectionNames) {
        for (String name : sectionNames) {
            sectionDao.addSectionDao(name);
        }
    }

}