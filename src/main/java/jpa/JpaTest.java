package jpa;

import dao.*;
import services.*;

import java.util.ArrayList;
import java.util.List;

public class JpaTest {

    /**
     * @param args
     */
    private static SectionDao sectionDao;
    private static DepartmentDao departmentDao;
    private static EmployeeDao employeeDao;
    private static CardDao cardDao;
    private static KanbanDao kanbanDao;


    public static void main(String[] args) {

        String[] SECTIONS_NAMES = new String[]{"to do", "in progress", "done"};
        String[] DEPARTMENTS_NAMES = new String[]{"Engineering", "Assembling", "Validation"};
        String[] EMPLOYEES_NAMES = new String[]{"John", "Wong", "Marie", "Sami", "Julie"};
        String[] CARDS_LABELS = new String[]{"task-1", "task-2", "task-3", "task-4", "task-5", "task-6", "task-7", "task-8"};

        sectionDao = new SectionDao();
        departmentDao = new DepartmentDao();
        employeeDao = new EmployeeDao();
        cardDao = new CardDao();
        kanbanDao = new KanbanDao();

        try {
            createKanban();
            Kanban kanban = kanbanDao.findAll().get(0);
            createDepartments(DEPARTMENTS_NAMES);
            List<Department> departments = departmentDao.findAll();
            createSections(SECTIONS_NAMES, kanban);

            List<Section> sections = sectionDao.findAll();

            createEmployees(EMPLOYEES_NAMES, departments);
            List<Employee> employees = employeeDao.findAll();

            createCards(CARDS_LABELS, employees, sections);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void createKanban() {
        Kanban kanban = new Kanban();
        kanban.setName("kanban");
        kanbanDao.save(kanban);
    }

    private static void createCards(String[] cardsLabels, List<Employee> employees, List<Section> sections) {
        List<String> dates = new ArrayList<>();
        dates.add("2021-02-03");
        dates.add("2023-02-03");
        dates.add("2024-02-03");

        for (String label : cardsLabels) {
            int d = (int) (Math.random() * dates.size());
            int e = (int) (Math.random() * employees.size());
            int s = (int) (Math.random() * sections.size());
            int duration = (int) (Math.random() * 100);

            Card card = new Card(label,
                    dates.get(d),
                    employees.get(e),
                    duration,
                    "tag-" + Integer.toString(duration),
                    sections.get(s),
                    "company.com");
            cardDao.save(card);
        }
    }

    private static void createEmployees(String[] employeesNames, List<Department> departments) {
        int i;
        for (String name : employeesNames) {
            i = (int) (Math.random() * departments.size());
            Employee employee = new Employee(name, departments.get(i));
            employeeDao.save(employee);
        }
    }

    private static void createDepartments(String[] departmentsNames) {
        for (String name : departmentsNames) {
            Department department = new Department();
            department.setName(name);
            departmentDao.save(department);
        }
    }

    private static void createSections(String[] sectionNames, Kanban kanban) {
        for (String name : sectionNames) {
            Section section = new Section();
            section.setName(name);
            section.setKanban(kanban);
            sectionDao.save(section);
        }
    }
}