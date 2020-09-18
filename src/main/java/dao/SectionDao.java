package dao;

import services.Section;

import java.util.List;

public interface SectionDao {
    public Section getSectionDao(long id);
    public void addSectionDao(String name);
    public void updateSectionDao(long id, String name);
    public void removeSectionDao(long id);
    public List<Section> getAllSectionsDao();
}