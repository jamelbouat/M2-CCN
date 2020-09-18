package services;
import javax.persistence.*;

@Entity
public class Employee {
    private long id;
    private String name;
    private Department department;
    private Card card;

    public Employee() {
    }

    public Employee(String name, Department department) {
        this.name = name;
        this.department = department;
    }

    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToOne
    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @OneToOne(mappedBy = "employee")
    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    @Override
    public String toString() {
        return "Employee [id=" + id + ", name=" + name + ", department="
                + department.getName() + "]";
    }

}
