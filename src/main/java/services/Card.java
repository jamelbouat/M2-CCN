package services;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Card implements Serializable {
    private long id;
    private String label;
    private Date deadline;
    private Employee employee;
    private int duration;
    private String tag;
    private Section section;
    private String url;

    public Card() {
    }

    public Card(String label, Date deadline, Employee employee, int duration, String tag, Section section, String url) {
        this.label = label;
        this.deadline = deadline;
        this.employee = employee;
        this.duration = duration;
        this.tag = tag;
        this.section = section;
        this.url = url;
    }

    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    @OneToOne
    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    @ManyToOne
    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
