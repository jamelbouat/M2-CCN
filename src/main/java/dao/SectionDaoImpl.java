package dao;

import jpa.EntityManagerHelper;
import services.Section;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;

public class SectionDaoImpl implements SectionDao {

    @Override
    public Section getSectionDao(long id) {
        EntityManager em = EntityManagerHelper.getEntityManager();
        String query = "SELECT section FROM Section section WHERE section.id = :sectionID";
        TypedQuery<Section> tq = em.createQuery(query, Section.class);
        tq.setParameter("sectionID", id);

        try {
            return tq.getSingleResult();

        } catch (NoResultException e) {
            throw new NoResultException("section does not exist");

        } finally {
//            EntityManagerHelper.closeEntityManager();
//            EntityManagerHelper.closeEntityManagerFactory();
        }
    }

    @Override
    public void addSectionDao(String name) {

        try {
            EntityManagerHelper.beginTransaction();

            Section section = new Section();
            section.setName(name);
            EntityManagerHelper.getEntityManager().persist(section);
            EntityManagerHelper.commit();
        } catch (Exception e) {

            e.printStackTrace();
        } finally {
//            EntityManagerHelper.closeEntityManager();
//            EntityManagerHelper.closeEntityManagerFactory();
        }
    }

    @Override
    public void updateSectionDao(long id, String name) {
        EntityManager em = EntityManagerHelper.getEntityManager();
        Section section;

        try {
            EntityManagerHelper.beginTransaction();
            section = em.find(Section.class, id);
            section.setName(name);
            EntityManagerHelper.getEntityManager().persist(section);
            EntityManagerHelper.commit();

        } catch (Exception e) {

            e.printStackTrace();
        } finally {
//            EntityManagerHelper.closeEntityManager();
//            EntityManagerHelper.closeEntityManagerFactory();
        }
    }

    @Override
    public void removeSectionDao(long id) {
        EntityManager em = EntityManagerHelper.getEntityManager();
        Section section;

        try {
            EntityManagerHelper.beginTransaction();
            section = em.find(Section.class, id);
            em.remove(section);
            EntityManagerHelper.commit();

        } catch (Exception e) {

            e.printStackTrace();
        } finally {
//            EntityManagerHelper.closeEntityManager();
//            EntityManagerHelper.closeEntityManagerFactory();
        }
    }

    @Override
    public List<Section> getAllSectionsDao() {
        EntityManager em = EntityManagerHelper.getEntityManager();

        String Query = "SELECT section FROM Section section WHERE section.id IS NOT NULL";
        TypedQuery<Section> tq = em.createQuery(Query, Section.class);

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
