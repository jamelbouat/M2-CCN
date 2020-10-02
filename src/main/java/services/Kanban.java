package services;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Kanban implements Serializable {
    private long id;
    private String name;
    private List<Section> sections = new ArrayList<>();

    public Kanban() {
    }

    public Kanban(String name, List<Section> sections) {
        this.name = name;
        this.sections = sections;
    }

    @Id
    @GeneratedValue
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany
    @JoinColumn(name = "kanban_id")
    public List<Section> getSections() {
        return sections;
    }

    public void setSections(List<Section> sections) {
        this.sections = sections;
    }
}