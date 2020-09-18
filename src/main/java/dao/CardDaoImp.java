package dao;

import jpa.EntityManagerHelper;
import services.Card;
import services.Employee;
import services.Section;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.Date;
import java.util.List;

public class CardDaoImp implements CardDao {
    @Override
    public Card getCard(long id) {
        EntityManager em = EntityManagerHelper.getEntityManager();
        String query = "SELECT card FROM Card card WHERE card.id = :cardID";
        TypedQuery<Card> tq = em.createQuery(query, Card.class);
        tq.setParameter("cardID", id);

        try {
            return tq.getSingleResult();

        } catch (NoResultException e) {
            throw new NoResultException("card does not exist");

        } finally {
//            EntityManagerHelper.closeEntityManager();
//            EntityManagerHelper.closeEntityManagerFactory();
        }
    }

    @Override
    public void addCard(String label, String deadline, Employee employee, int duration, String tag, Section section, String url) {

        try {
            EntityManagerHelper.beginTransaction();

            Card card = new Card(label, deadline, employee, duration, tag, section, url);
            card.setEmployee(employee);
            card.setSection(section);

            EntityManagerHelper.getEntityManager().persist(card);
            EntityManagerHelper.commit();
        } catch (Exception e) {

            e.printStackTrace();
        } finally {
//            EntityManagerHelper.closeEntityManager();
//            EntityManagerHelper.closeEntityManagerFactory();
        }
    }

    @Override
    public void updateCard(long id, String label, Section section) {
        EntityManager em = EntityManagerHelper.getEntityManager();
        Card card;

        try {
            EntityManagerHelper.beginTransaction();
            card = em.find(Card.class, id);
            card.setLabel(label);
            card.setSection(section);

            EntityManagerHelper.getEntityManager().persist(card);
            EntityManagerHelper.commit();

        } catch (Exception e) {

            e.printStackTrace();
        } finally {
//            EntityManagerHelper.closeEntityManager();
//            EntityManagerHelper.closeEntityManagerFactory();
        }

    }

    @Override
    public void removeCard(long id) {
        EntityManager em = EntityManagerHelper.getEntityManager();
        Card card;

        try {
            EntityManagerHelper.beginTransaction();
            card = em.find(Card.class, id);
            em.remove(card);
            EntityManagerHelper.commit();

        } catch (Exception e) {

            e.printStackTrace();
        } finally {
//            EntityManagerHelper.closeEntityManager();
//            EntityManagerHelper.closeEntityManagerFactory();
        }
    }

    @Override
    public List<Card> getAllCards() {
        EntityManager em = EntityManagerHelper.getEntityManager();

        String Query = "SELECT card FROM Card card WHERE card.id IS NOT NULL";
        TypedQuery<Card> tq = em.createQuery(Query, Card.class);

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
