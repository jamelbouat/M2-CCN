package dao;

import services.Kanban;

public class KanbanDao extends AbstractJpaDao<Long, Kanban>{

    public KanbanDao() {
        super(Kanban.class);
    }
}