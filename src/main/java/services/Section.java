package services;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Section {
    private long id;
    private String name;
    private Kanban kanban;
    private List<Card> cards = new ArrayList<Card>();

    public Section() {
    }

    public Section(long id, String name) {
        this.id = id;
        this.name = name;
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

    @ManyToOne()
    public Kanban getKanban() {
        return kanban;
    }

    public void setKanban(Kanban kanban) {
        this.kanban = kanban;
    }

    @OneToMany(mappedBy = "section")
    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }
}
