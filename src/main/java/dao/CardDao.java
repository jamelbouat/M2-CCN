package dao;

import services.Card;
import services.Employee;
import services.Section;

import java.util.Date;
import java.util.List;

public interface CardDao {
    public Card getCard(long id);
    public void addCard(String label, String deadline, Employee employee, int duration, String tag, Section section, String url);
    public void updateCard(long id, String label, Section section);
    public void removeCard(long id);
    public List<Card> getAllCards();
}
